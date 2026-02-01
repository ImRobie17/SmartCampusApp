package com.example.smartcampusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.smartcampusapp.ui.navigation.AppNav
import com.example.smartcampusapp.ui.theme.SmartCampusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Using dynamicColor = false to ensure your custom colors show up
            SmartCampusAppTheme(dynamicColor = false) {
                AppNav()
            }
        }
    }
}