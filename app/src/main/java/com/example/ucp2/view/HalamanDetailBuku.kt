package com.example.ucp2.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.viewmodel.BukuEvent
import com.example.ucp2.viewmodel.DetailBukuViewModel
import com.example.ucp2.viewmodel.provider.PenyediaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBukuScreen(
    navigateBack: () -> Unit,
    onEditClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailBukuViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.detailBukuUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            LibraryTopAppBar(
                title = "Detail Buku",
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(uiState.bukuEvent.id) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Buku"
                )
            }
        },
        modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            bukuEvent = uiState.bukuEvent,
            onDelete = { deleteConfirmationRequired = true },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    coroutineScope.launch {
                        viewModel.deleteBuku()
                        navigateBack()
                    }
                },
                onDeleteCancel = { deleteConfirmationRequired = false }
            )
        }
    }
}

@Composable
fun ItemDetailsBody(
    bukuEvent: BukuEvent,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        ItemDetailsCard(bukuEvent = bukuEvent)
        TextButton(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Hapus Buku", color = Color.Red)
        }
    }
}

@Composable
fun ItemDetailsCard(
    bukuEvent: BukuEvent,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {
            DetailRow(label = "Judul", value = bukuEvent.judul)
            DetailRow(label = "ISBN", value = bukuEvent.isbn)
            DetailRow(label = "Pengarang", value = bukuEvent.nama_pengarang)
            DetailRow(label = "Kategori", value = bukuEvent.nama_kategori)
            DetailRow(label = "Status", value = bukuEvent.status_pinjam)
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "$label: ", fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.perhatian)) },
        text = { Text(stringResource(R.string.konfirmasi_hapus)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Batal")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Hapus")
            }
        }
    )
}
