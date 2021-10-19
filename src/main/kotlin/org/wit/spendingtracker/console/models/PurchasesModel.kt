package org.wit.spendingtracker.console.models

data class PurchasesModel(
    var id: Long = 0,
    var description: String = "",
    var amount: String = "")
