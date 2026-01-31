package com.example.ucp2.view.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2.view.EntryBukuScreen
import com.example.ucp2.view.EntryPengarangScreen
import com.example.ucp2.view.LibraryHomeScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(DestinasiHome.route) {
            LibraryHomeScreen(
                onAddPengarangClick = { navController.navigate(DestinasiEntryPengarang.route) },
                onAddBukuClick = { navController.navigate(DestinasiEntryBuku.route) }
            )
        }
        composable(DestinasiEntryPengarang.route) {
            EntryPengarangScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(DestinasiEntryBuku.route) {
            EntryBukuScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
