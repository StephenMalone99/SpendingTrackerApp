package org.wit.spendingtracker.console.models
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PurchaseMemStore : PurchaseStore {

        val purchases = ArrayList<PurchasesModel>()

        override fun findAll(): List<PurchasesModel> {
            return purchases
        }

        override fun findOne(id: Long) : PurchasesModel? {
                var foundPurchase: PurchasesModel? = purchases.find { p -> p.id == id }
            return foundPurchase
        }

        override fun create(purchase: PurchasesModel) {
            purchase.id = getId()
            purchases.add(purchase)
            logAll()
        }

        override fun update(purchase: PurchasesModel) {
            var foundPurchase = findOne(purchase.id!!)
            if (foundPurchase != null) {
                foundPurchase.description = purchase.description
                foundPurchase.amount = purchase.amount
            }
        }

        override fun delete(purchase: PurchasesModel) {
            purchases.remove(purchase)
        }

        internal fun logAll() {
            purchases.forEach { logger.info("${it}") }
        }
    }
