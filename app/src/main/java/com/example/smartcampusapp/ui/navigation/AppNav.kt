package com.example.smartcampusapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartcampusapp.ui.screens.CampusInfoScreen
import com.example.smartcampusapp.ui.screens.DashboardScreen
import com.example.smartcampusapp.ui.screens.LoginScreen
import com.example.smartcampusapp.util.SessionManager

object Routes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val CAMPUS_INFO = "campus_info"
}

@Composable
fun AppNav() {
    val context = LocalContext.current
    val session = SessionManager(context)

    val navController = rememberNavController()
    val startDestination = if (session.isLoggedIn()) Routes.DASHBOARD else Routes.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = { studentId ->
                    session.saveSession(studentId)
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                studentId = session.getStudentId(),
                onOpenCampusInfo = { navController.navigate(Routes.CAMPUS_INFO) },
                onLogout = {
                    session.clearSession()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.DASHBOARD) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.CAMPUS_INFO) {
            CampusInfoScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
