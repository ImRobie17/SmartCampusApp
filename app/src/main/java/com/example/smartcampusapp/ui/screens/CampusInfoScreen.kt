package com.example.smartcampusapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Department(val name: String, val contact: String, val email: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusInfoScreen(onBack: () -> Unit) {
    val departments = listOf(
        Department("Office of the Registrar", "0917-000-0001", "registrar@campus.edu"),
        Department("Student Affairs Office", "0917-000-0002", "sao@campus.edu"),
        Department("IT Department", "0917-000-0003", "it@campus.edu")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campus Information") },
                navigationIcon = { IconButton(onClick = onBack) { Text("Back") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(departments) { dept ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = dept.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = "Contact: ${dept.contact}")
                        Text(text = "Email: ${dept.email}")
                    }
                }
            }
        }
    }
}