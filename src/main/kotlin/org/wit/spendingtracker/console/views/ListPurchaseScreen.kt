package org.wit.spendingtracker.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.spendingtracker.console.controllers.PurchaseUIController
import org.wit.spendingtracker.console.models.PurchasesModel
import tornadofx.*

class ListPurchaseScreen : View("List Placemarks") {

    val purchaseUIController: PurchaseUIController by inject()
    val tableContent = purchaseUIController.purchases.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", PurchasesModel::id)
            readonlyColumn("Description of Purchase", PurchasesModel::description)
            readonlyColumn("Cost of Purchase", PurchasesModel::amount)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    purchaseUIController.closeList()
                }
            }
        }
    }

}