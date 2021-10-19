package org.wit.spendingtracker.console.controllers


import mu.KotlinLogging
import org.wit.spendingtracker.console.models.PurchaseJSONStore
import org.wit.spendingtracker.console.models.PurchasesModel
import org.wit.spendingtracker.console.views.AddPurchaseScreen
import org.wit.spendingtracker.console.views.ListPurchaseScreen
import org.wit.spendingtracker.console.views.MenuScreen
import tornadofx.*

class PurchaseUIController : Controller() {

    val purchases = PurchaseJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Spending Tracker TornadoFX UI App" }
    }

    fun add(_description: String, _amount: String) {

        var aPurchase = PurchasesModel(description = _description, amount = _amount)
        purchases.create(aPurchase)
        logger.info("Purchase Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListPurchaseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        purchases.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddPurchaseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddPurchaseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeList() {
        runLater {
            find(ListPurchaseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}

