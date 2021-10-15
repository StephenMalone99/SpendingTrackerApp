package org.wit.spendingtracker.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.spendingtracker.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "purchases.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PurchasesModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PurchaseJSONStore : PurchaseStore {

    var purchases = mutableListOf<PurchasesModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PurchasesModel> {
        return purchases
    }

    override fun findOne(id: Long) : PurchasesModel? {
        var foundPurchase: PurchasesModel? = purchases.find { p -> p.id == id }
        return foundPurchase
    }

    override fun create(purchase: PurchasesModel) {
        purchase.id = generateRandomId()
        purchases.add(purchase)
        serialize()
    }

    override fun update(purchase: PurchasesModel) {
        var foundPurchase = findOne(purchase.id!!)
        if (foundPurchase != null) {
            foundPurchase.description = purchase.description
            foundPurchase.amount = purchase.amount
        }
        serialize()
    }

    override fun delete(purchase: PurchasesModel) {
        purchases.remove(purchase)
        serialize()
    }

    internal fun logAll() {
        purchases.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(purchases, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        purchases = Gson().fromJson(jsonString, listType)
    }
}