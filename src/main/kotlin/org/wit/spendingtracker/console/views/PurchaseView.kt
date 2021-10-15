package org.wit.spendingtracker.console.views

import org.wit.spendingtracker.console.models.PurchaseJSONStore
import org.wit.spendingtracker.console.models.PurchaseMemStore
import org.wit.spendingtracker.console.models.PurchasesModel

class PurchaseView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Purchase")
        println(" 2. Update Purchase")
        println(" 3. List All Purchases")
        println(" 4. Search Purchase")
        println(" 5. Delete Purchase")
        println(" 6. Load dummy data")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listPurchases(purchases : PurchaseJSONStore) {
        println("List All Purchases")
        println()
        purchases.logAll()
        println()
    }

    fun showPurchase(purchase : PurchasesModel) {
        if(purchase != null)
            println("Purchase Details [ $purchase ]")
        else
            println("Purchase Not Found...")
    }

    fun addPurchaseData(purchase: PurchasesModel) : Boolean {

        println()
        print("Enter a Description of the Purchase : ")
        purchase.description = readLine()!!
        print("Enter the amount spent : ")
        purchase.amount = readLine()!!.toInt()

        return purchase.description.isNotEmpty() && purchase.amount.toString().isNotEmpty()
    }

    fun updatePurchaseData(purchase: PurchasesModel) : Boolean {

        var tempDescription: String?
        var tempAmount: Int?

        if (purchase != null) {
            print("Enter a new Description for [ " + purchase.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new amount for [ " + purchase.amount + " ] : ")
            tempAmount = readLine()!!.toInt()

            if (!tempDescription.isNullOrEmpty() && !tempAmount.toString().isNullOrEmpty()) {
                purchase.description = tempDescription
                purchase.amount = tempAmount
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}