package com.example.ucp2.view.route

import com.example.ucp2.R

object DestinasiHome : DestinasiNavigasi {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

object DestinasiEntryPengarang : DestinasiNavigasi {
    override val route: String = "entry_pengarang"
    override val titleRes: Int = R.string.entry_pengarang
}

object DestinasiEntryBuku : DestinasiNavigasi {
    override val route: String = "entry_buku"
    override val titleRes: Int = R.string.entry_buku
}

object DestinasiListPengarang : DestinasiNavigasi {
    override val route: String = "list_pengarang"
    override val titleRes: Int = R.string.home_pengarang
}

object DestinasiListBuku : DestinasiNavigasi {
    override val route: String = "list_buku"
    override val titleRes: Int = R.string.home_buku
}

object DestinasiDetailBuku : DestinasiNavigasi {
    override val route: String = "detail_buku"
    override val titleRes: Int = R.string.app_name
    const val bukuIdArg = "bukuId"
    val routeWithArgs = "$route/{$bukuIdArg}"
}

object DestinasiEditBuku : DestinasiNavigasi {
    override val route: String = "edit_buku"
    override val titleRes: Int = R.string.app_name
    const val bukuIdArg = "bukuId"
    val routeWithArgs = "$route/{$bukuIdArg}"
}

object DestinasiDetailPengarang : DestinasiNavigasi {
    override val route: String = "detail_pengarang"
    override val titleRes: Int = R.string.app_name
    const val pengarangIdArg = "pengarangId"
    val routeWithArgs = "$route/{$pengarangIdArg}"
}

object DestinasiEditPengarang : DestinasiNavigasi {
    override val route: String = "edit_pengarang"
    override val titleRes: Int = R.string.app_name
    const val pengarangIdArg = "pengarangId"
    val routeWithArgs = "$route/{$pengarangIdArg}"
}
