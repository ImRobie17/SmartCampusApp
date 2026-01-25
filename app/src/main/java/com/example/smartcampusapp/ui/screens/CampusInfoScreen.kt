package com.example.smartcampusapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class Department(
    val name: String,
    val contact: String,
    val email: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusInfoScreen(
    onBack: () -> Unit
) {
    val departments = listOf(
        Department("Office of the Registrar", "0917-000-0001", "registrar@campus.edu"),
        Department("Student Affairs Office", "0917-000-0002", "sao@campus.edu"),
        Department("IT Department", "0917-000-0003", "it@campus.edu"),
        Department("Library Services", "0917-000-0004", "library@campus.edu"),
        Department("Guidance & Counseling", "0917-000-0005", "guidance@campus.edu")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Campus Information") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(departments) { dept ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors()
                ) {
                    Text(
                        text = dept.name,
                        modifier = Modifier.padding(start = 14.dp, top = 14.dp, end = 14.dp)
                    )
                    Text(
                        text = "Contact: ${dept.contact}",
                        modifier = Modifier.padding(start = 14.dp, top = 6.dp, end = 14.dp)
                    )
                    Text(
                        text = "Email: ${dept.email}",
                        modifier = Modifier.padding(start = 14.dp, top = 4.dp, end = 14.dp, bottom = 14.dp)
                    )
                }
            }
        }
    }
}
