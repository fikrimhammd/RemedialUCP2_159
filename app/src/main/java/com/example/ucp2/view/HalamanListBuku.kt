package com.example.ucp2.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.room.Buku
import com.example.ucp2.view.route.DestinasiListBuku
import com.example.ucp2.viewmodel.HomeBukuViewModel
import com.example.ucp2.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBukuScreen(
    navigateBack: () -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val homeUiState by viewModel.homeBukuUiState.collectAsState()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LibraryTopAppBar(
                title = stringResource(DestinasiListBuku.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        ListBukuBody(
            listBuku = homeUiState.listBuku,
            onItemClick = onItemClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ListBukuBody(
    listBuku: List<Buku.Buku>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (listBuku.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.data_kosong))
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
            ) {
                items(listBuku) { buku ->
                    BukuCard(buku = buku, onClick = { onItemClick(buku.id) })
                }
            }
        }
    }
}

@Composable
fun BukuCard(
    buku: Buku.Buku,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = buku.judul,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = buku.status_pinjam,
                    color = if (buku.status_pinjam == "Tersedia") Color.Green else Color.Red,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(text = "ISBN: ${buku.isbn}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Pengarang: ${buku.nama_pengarang}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Kategori: ${buku.nama_kategori}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
