package com.example.smartcampusapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.GridView // Reliable alternative for 'Dashboard' icon
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcampusapp.ui.theme.Purple80 // Uses Purple80 from your theme
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    studentId: String,
    onOpenCampusInfo: () -> Unit,
    onLogout: () -> Unit
) {
    val bgPurple = Color(0xFF4E4AB8) // Current background purple
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val textcolor = Color(0xFF111111)


    // Forces Right-to-Left for the Drawer position
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                // Switch back to Left-to-Right for the text inside the menu
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    ModalDrawerSheet(
                        // Fixed: 'containerColor' is the correct property for Material 3 ModalDrawerSheet
                        drawerContainerColor = Color(0xFF4E4AB8),
                        modifier = Modifier.width(300.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
                            Spacer(Modifier.height(32.dp))

                            // Close Menu Item
                            NavigationDrawerItem(
                                label = { Text("Close Menu", color = Color.White) },
                                selected = false,
                                onClick = { scope.launch { drawerState.close() } },
                                icon = { Icon(Icons.Default.Close, null, tint = Color.White) },
                                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                            )

                            // Profile Icon Item
                            NavigationDrawerItem(
                                label = { Text("Profile icon", color = Color.White) },
                                selected = false,
                                onClick = { /* Navigate to Profile */ },
                                icon = { Icon(Icons.Default.Person, null, tint = Color.White) },
                                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                            )

                            // Dashboard Item (Uses GridView icon substitute)
                            NavigationDrawerItem(
                                label = { Text("Dashboard", color = Color.White) },
                                selected = true,
                                onClick = { scope.launch { drawerState.close() } },
//                                icon = { Icon(Icons.Default.GridView, null, tint = Color.White) },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color.White.copy(alpha = 0.2f),
                                    selectedTextColor = Color.White,
                                    selectedIconColor = Color.White,
                                    unselectedContainerColor = Color.Transparent
                                )
                            )

                            // Campus Information Item
                            NavigationDrawerItem(
                                label = { Text("Campus Information", color = Color.White) },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    onOpenCampusInfo()
                                },
                                icon = { Icon(Icons.Default.Home, null, tint = Color.White) },
                                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                            )

                            Spacer(modifier = Modifier.weight(1f)) // Pushes Logout to bottom

                            // Logout Item inside Menu
                            NavigationDrawerItem(
                                label = { Text("Logout", color = Color.White) },
                                selected = false,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    onLogout()
                                },
                                icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, null, tint = Color.White) },
                                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent)
                            )
                        }
                    }
                }
            }
        ) {
            // Main content must be Ltr
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(bgPurple)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Header Section
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 48.dp, bottom = 24.dp, start = 24.dp, end = 24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Smart Campus", fontSize = 14.sp, color = Color.White.copy(alpha = 0.8f))
                                Text("Dashboard", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }

                            // Menu Icon (Now on the right)
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
                                    .size(50.dp)
                                    .background(Color.White.copy(alpha = 0.2f), CircleShape)
                            ) {
                                Icon(Icons.Default.Menu, "Open Menu", tint = Color.White)
                            }
                        }

                        // Welcome Card
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF6A67C9))
                        ) {
                            Column(modifier = Modifier.padding(24.dp)) {
                                Text("Welcome back,", color = Color.White.copy(alpha = 0.8f))
                                // Fixed: 'ifBlank' is the idiomatic way to handle empty studentId
                                Text(studentId.ifBlank { "Student" }, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Services Section
                        Text("SERVICES", fontSize = 14.sp, color = Color.White.copy(alpha = 0.5f), modifier = Modifier.padding(start = 24.dp))

                        Row(modifier = Modifier.padding(24.dp)) {
                            DashboardCard(
                                title = "Campus\nInfo",
                                icon = Icons.Default.Home,
                                backgroundColor = Purple80,
                                iconColor = Color.White,
                                modifier = Modifier.weight(1f),
                                onClick = onOpenCampusInfo
                            )
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

// Added back to resolve 'Unresolved reference DashboardCard'
@Composable
fun DashboardCard(
    title: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111111),
                lineHeight = 20.sp
            )
        }
    }
}