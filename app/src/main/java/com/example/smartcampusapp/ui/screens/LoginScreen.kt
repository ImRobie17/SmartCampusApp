package com.example.smartcampusapp.ui.screens // Adjust this if your package is different

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartcampusapp.R // Ensure this imports your actual R class

@Composable
fun LoginScreen(
    onLoginSuccess: (studentId: String) -> Unit
) {
    // Gradient colors based on your design
    val gradientColors = listOf(
        Color(0xFF6A0DAD), // Dark Purple
        Color(0xFFC71585)  // Magenta/Pink
    )
    val textColor = Color.White

    // State variables for user input
    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") } // State for error messages

    // Root Box to layer everything
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        // --- 1. The Robots Background ---

        // Top Robot (Top Right)
        Image(
            painter = painterResource(id = R.drawable.robot_down),
            contentDescription = "Top Robot Mascot",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(190.dp)
                .offset(x = (-10).dp, y = 10.dp) // Pulls it left and down into the screen
        )

        // Bottom Robot (Bottom Left)
        Image(
            painter = painterResource(id = R.drawable.robot_up),
            contentDescription = "Bottom Robot Mascot",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(190.dp)
                .offset(x = 10.dp, y = (-20).dp) // Pulls it right and up into the screen
        )

        // --- 2. The Login Form ---
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .align(Alignment.Center)
                .padding(bottom = 50.dp), // Shifts the form up slightly
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Welcome back!",
                color = textColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Login to your account",
                color = textColor.copy(alpha = 0.9f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Username Field
            OutlinedTextField(
                value = studentId,
                onValueChange = {
                    studentId = it
                    errorMessage = "" // Clear error when typing
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Username", color = textColor.copy(alpha = 0.7f)) },
                leadingIcon = {
                    Icon(Icons.Filled.Person, contentDescription = "User Icon", tint = textColor)
                },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor.copy(alpha = 0.6f),
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    cursorColor = textColor
                ),
                singleLine = true
            )

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = "" // Clear error when typing
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Password", color = textColor.copy(alpha = 0.7f)) },
                leadingIcon = {
                    Icon(Icons.Filled.Lock, contentDescription = "Lock Icon", tint = textColor)
                },
                trailingIcon = {
                    val image = if (passwordVisible) "Hide" else "Show"
                    Text(
                        text = image,
                        color = textColor.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { passwordVisible = !passwordVisible }
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor.copy(alpha = 0.6f),
                    focusedTextColor = textColor,
                    unfocusedTextColor = textColor,
                    cursorColor = textColor
                ),
                singleLine = true
            )

            // Show error message if login fails
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color(0xFFFFB4AB), // A light red/pink that looks good on dark backgrounds
                    fontSize = 14.sp
                )
            }

            // Forgot Password
            Text(
                text = "Forgot Password?",
                color = textColor,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { }
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // LOGIN BUTTON
                Button(
                    onClick = {
                        // Hardcoded validation (Phase 1: mock authentication)
                        // Change these to your preferred test credentials:
                        val validStudentId = "2300590"
                        val validPassword = "password123"

                        if (studentId.trim() == validStudentId && password == validPassword) {
                            onLoginSuccess(studentId.trim())
                        } else {
                            errorMessage = "Invalid credentials. Try 2025-0001 / password123"
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Login", color = Color(0xFF6A0DAD), fontWeight = FontWeight.Bold)
                }

                // SIGN UP BUTTON
                OutlinedButton(
                    onClick = {
//                        onNavigateToSignUp() // Triggers navigation to Sign Up screen
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Sign Up", color = Color.White)
                }
            }
        }
    }
}