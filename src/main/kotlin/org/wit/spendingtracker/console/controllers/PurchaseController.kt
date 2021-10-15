package org.wit.spendingtracker.console.controllers

import mu.KotlinLogging
import org.wit.spendingtracker.console.models.PurchaseMemStore
import org.wit.spendingtracker.console.models.PurchasesModel
import org.wit.spendingtracker.console.views.PurchaseView

class PurchaseController {

    val purchases = PurchaseMemStore()
    val purchaseView = PurchaseView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Spending Tracker Assignment 1 Console App" }
        println("Kotlin App Version")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Spending Tracker Console App" }
    }

    fun menu() :Int { return purchaseView.menu() }

    fun add(){
        var aPurchase = PurchasesModel()

        if (purchaseView.addPurchaseData(aPurchase))
            purchases.create(aPurchase)
        else
            logger.info("Purchase Not Added")
    }

    fun list() {
        purchaseView.listPurchases(purchases)
    }

    fun update() {

        purchaseView.listPurchases(purchases)
        var searchId = purchaseView.getId()
        val aPurchase = search(searchId)

        if(aPurchase != null) {
            if(purchaseView.updatePurchaseData(aPurchase)) {
                purchases.update(aPurchase)
                purchaseView.showPurchase(aPurchase)
                logger.info("Purchase Updated : [ $aPurchase ]")
            }
            else
                logger.info("Purchase Not Updated")
        }
        else
            println("Purchase Not Updated...")
    }

    fun search() {
        val aPurchase = search(purchaseView.getId())!!
        purchaseView.showPurchase(aPurchase)
    }


    fun search(id: Long) : PurchasesModel? {
        var foundPurchase = purchases.findOne(id)
        return foundPurchase
    }

    fun dummyData() {
        purchases.create(PurchasesModel(description= "North face hat", amount = 20))
        purchases.create(PurchasesModel(description= "Adidas Hoodie", amount = 50))
        purchases.create(PurchasesModel(description= "Nike t-shirt", amount = 35))
    }
}