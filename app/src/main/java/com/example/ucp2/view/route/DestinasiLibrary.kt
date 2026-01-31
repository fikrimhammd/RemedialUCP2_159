package com.example.ucp2.view.route

import com.example.ucp2.R

object DestinasiHome : DestinasiNavigasi {
    override val route: String = "home"
    override val titleRes: Int = R.string.home_buku
}

object DestinasiEntryPengarang : DestinasiNavigasi {
    override val route: String = "entry_pengarang"
    override val titleRes: Int = R.string.entry_pengarang
}

object DestinasiEntryBuku : DestinasiNavigasi {
    override val route: String = "entry_buku"
    override val titleRes: Int = R.string.entry_buku
}
