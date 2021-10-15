package org.wit.spendingtracker.console.models

interface PurchaseStore {
    fun findAll(): List<PurchasesModel>
    fun findOne(id: Long): PurchasesModel?
    fun create(purchase: PurchasesModel)
    fun update(purchase: PurchasesModel)
    fun delete(purchase: PurchasesModel)
}