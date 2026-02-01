package com.example.smartcampusapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class Department(val name: String, val contact: String, val email: String)

@Composable
fun CampusInfoScreen(onBack: () -> Unit) {
    // Theme Colors
    val bgPurple = Color(0xFF4E4AB8)
    val cardFill = Color(0xFFB9B7E6)
    val textColor = Color(0xFF111111)

    val departments = listOf(
        Department("Office of the Registrar", "0917-000-0001", "registrar@campus.edu"),
        Department("Student Affairs Office", "0917-000-0002", "sao@campus.edu"),
        Department("IT Department", "0917-000-0003", "it@campus.edu"),
        Department("Library Services", "0917-000-0004", "library@campus.edu"),
        Department("Guidance & Counseling", "0917-000-0005", "guidance@campus.edu")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgPurple) // Purple Background
    ) {
        // Custom Top Bar with Back Arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack,
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(28.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Campus Information",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // List of Departments
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(departments) { dept ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = cardFill),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = dept.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "📞  ${dept.contact}", color = Color.DarkGray)
                        Text(text = "✉️  ${dept.email}", color = Color.DarkGray)
                    }
                }
            }
        }
    }
}