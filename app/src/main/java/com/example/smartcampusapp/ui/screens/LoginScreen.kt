package com.example.smartcampusapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginSuccess: (studentId: String) -> Unit
) {
    val bgPurple = Color(0xFF4E4AB8)
    val cardFill = Color(0xFFB9B7E6)
    val cardBorder = Color(0xFF1ED0FF)
    val fieldFill = Color(0xFF0AA0D8)
    val fieldText = Color(0xFF0A0A0A)
    val buttonFill = Color(0xFFEAE8FF)
    val buttonBorder = Color(0xFF6A67C9)

    var studentId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgPurple),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(260.dp)
                .border(width = 2.dp, color = cardBorder, shape = RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = cardFill),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF111111)
                )

                Spacer(modifier = Modifier.height(22.dp))

                TextField(
                    value = studentId,
                    onValueChange = { studentId = it; errorText = null },
                    placeholder = { Text("Student ID", color = Color(0xFF063A4A), fontSize = 13.sp) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = fieldText, fontSize = 13.sp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = fieldFill,
                        unfocusedContainerColor = fieldFill,
                        disabledContainerColor = fieldFill,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                    modifier = Modifier.width(210.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                TextField(
                    value = password,
                    onValueChange = { password = it; errorText = null },
                    placeholder = { Text("Password", color = Color(0xFF063A4A), fontSize = 13.sp) },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = fieldText, fontSize = 13.sp),
                    visualTransformation = if (password.isEmpty()) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = fieldFill,
                        unfocusedContainerColor = fieldFill,
                        disabledContainerColor = fieldFill,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    shape = RoundedCornerShape(6.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    modifier = Modifier.width(210.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (errorText != null) {
                    Text(text = errorText!!, color = Color(0xFF7A0000), fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                OutlinedButton(
                    onClick = {
                        val validStudentId = "2300590"
                        val validPassword = "password123"
                        if (studentId.trim() == validStudentId && password == validPassword) {
                            onLoginSuccess(studentId.trim())
                        } else {
                            errorText = "Invalid credentials."
                        }
                    },
                    shape = RoundedCornerShape(50),
                    border = androidx.compose.foundation.BorderStroke(1.dp, buttonBorder),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = buttonFill, contentColor = Color(0xFF2D2B66)),
                    modifier = Modifier.width(110.dp)
                ) {
                    Text(text = "Login", fontSize = 13.sp)
                }
            }
        }
    }
}