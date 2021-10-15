package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.spendingtracker.console.controllers.PurchaseController
import org.wit.spendingtracker.console.models.PurchaseMemStore
import org.wit.spendingtracker.console.models.PurchasesModel
import org.wit.spendingtracker.console.views.PurchaseView

private val logger = KotlinLogging.logger {}

val purchases = PurchaseMemStore()
val purchaseView = PurchaseView()
val controller = PurchaseController()

fun main(args: Array<String>) {
    controller.start()
}

