package org.wit.spendingtracker.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Orientation
import org.wit.spendingtracker.console.controllers.PurchaseUIController
import tornadofx.*

class AddPurchaseScreen : View("Add Purchase") {
    val model = ViewModel()
    val _description = model.bind { SimpleStringProperty() }
    val _amount = model.bind { SimpleIntegerProperty() }
    val purchaseUIController: PurchaseUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Description of Purchase") {
                textfield(_description).required()
            }
            field("Cost of Purchase") {
                textfield(_amount).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        purchaseUIController.add(_description.toString(),  _amount.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        purchaseUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _description.value = ""
        _amount.value = 0
        model.clearDecorators()
    }
}