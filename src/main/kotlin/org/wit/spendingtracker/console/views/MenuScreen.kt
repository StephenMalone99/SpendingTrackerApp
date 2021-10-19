package org.wit.spendingtracker.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.spendingtracker.console.controllers.PurchaseUIController
import tornadofx.*

class MenuScreen : View("Placemark Main Menu") {

    val placemarkUIController: PurchaseUIController by inject()

    override val root = form {
        setPrefSize(500.0, 300.0)
        text("Spending Tracker App Assignment 1")
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Placemark") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        placemarkUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Placemarks") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        placemarkUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit Application") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }}