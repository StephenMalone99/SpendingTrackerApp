package org.wit.spendingtracker.console.views

import org.wit.spendingtracker.console.models.PurchaseJSONStore
import org.wit.spendingtracker.console.models.PurchasesModel


class PurchaseView {

    val ANSI_RESET = "\u001B[0m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITEBACKGROUND = "\u001B[47m\n"


    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_RED + "MAIN MENU" + ANSI_RESET)
        println(ANSI_GREEN +  " 1. Add Purchase" + ANSI_RESET)
        println(ANSI_BLUE +   " 2. Update Purchase" + ANSI_RESET)
        println(ANSI_YELLOW + " 3. List All Purchases" + ANSI_RESET)
        println(ANSI_PURPLE + " 4. Search Purchase" + ANSI_RESET)
        println(ANSI_CYAN +   " 5. Delete Purchase" + ANSI_RESET)
        println(ANSI_BLUE  + " 6. Load dummy data" + ANSI_RESET)
        println(ANSI_RED +    "-1. Exit" + ANSI_RESET)
        println()
        print(ANSI_GREEN + "Enter Option : " + ANSI_RESET)
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

        val pattern = "^[0-9]*\$".toRegex()
        println()
        print("Enter a Description of the Purchase : ")
        purchase.description = readLine()!!
        print("Enter the amount spent : ")
        purchase.amount = readLine()!!

        val string = purchase.amount
        val string2 = purchase.description
        var numeric = true
        numeric = string.matches("^[0-9]*\$".toRegex()) && string2.matches("^[a-zA-Z0-9_ ]*\$".toRegex())

        if (numeric) {
            return purchase.description.isNotEmpty() && purchase.amount.isNotEmpty()
        }
        return false
    }

    fun updatePurchaseData(purchase: PurchasesModel) : Boolean {

        var tempDescription: String?
        var tempAmount: String?

        if (purchase != null) {
            print("Enter a new Description for [ " + purchase.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new amount for [ " + purchase.amount + " ] : ")
            tempAmount = readLine()!!

            if (!tempDescription.isNullOrEmpty() && !tempAmount.isNullOrEmpty()) {
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
        print("Enter id to Search/Update or Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}