package com.example.ucp2.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonSearch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R
import com.example.ucp2.view.route.DestinasiHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryHomeScreen(
    onAddPengarangClick: () -> Unit,
    onAddBukuClick: () -> Unit,
    onListPengarangClick: () -> Unit,
    onListBukuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            LibraryTopAppBar(
                title = stringResource(DestinasiHome.titleRes),
                canNavigateBack = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Sistem Manajemen Perpustakaan",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            
            Spacer(modifier = Modifier.size(16.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)) {
                MenuCard(
                    title = "Tambah Pengarang",
                    icon = Icons.Default.Person,
                    onClick = onAddPengarangClick,
                    modifier = Modifier.weight(1f)
                )
                MenuCard(
                    title = "Tambah Buku",
                    icon = Icons.Default.Book,
                    onClick = onAddBukuClick,
                    modifier = Modifier.weight(1f)
                )
            }
            
            MenuCard(
                title = "Daftar Pengarang",
                description = "Lihat semua pengarang yang terdaftar",
                icon = Icons.Default.PersonSearch,
                onClick = onListPengarangClick
            )
            
            MenuCard(
                title = "Daftar Buku",
                description = "Lihat semua koleksi buku",
                icon = Icons.Default.List,
                onClick = onListBukuClick
            )
        }
    }
}

@Composable
fun MenuCard(
    title: String,
    description: String = "",
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            if (description.isNotEmpty()) {
                Text(text = description, style = MaterialTheme.typography.bodySmall, textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }
    }
}
