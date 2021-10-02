package org.wit.spendingtracker.console.main
import mu.KotlinLogging
import org.wit.spendingtracker.console.models.PurchasesModel

private val logger = KotlinLogging.logger {}

val purchases = ArrayList<PurchasesModel>()


fun main(args: Array<String>) {
    logger.info { "Launching my Spending Tracker App" }
    println("This is Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addPurchase()
            2 -> updatePurchase()
            3 -> listPurchases()
            4 -> searchPurchases()
            5 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Spending Tracking Console App" }
}


fun menu() : Int {

    var option : Int
    var input: String?

    println("Welcome to the Main Menu")
    println(" 1. Add a Purchase")
    println(" 2. Update a Purchase")
    println(" 3. List All Purchases")
    println(" 4. Search Purchases")
    println(" 5. Load the dummy data")
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

fun addPurchase(){
    var aPurchases = PurchasesModel()
    println("Add Placemark")
    println()
    print("Enter a description of the purchase : ")
    aPurchases.description = readLine()!!
    print("Enter the amount : ")
    aPurchases.amount = readLine()!!.toInt()

    if (aPurchases.description.isNotEmpty() && aPurchases.amount.toString().isNotEmpty()) {
        aPurchases.id = purchases.size.toLong()
        purchases.add(aPurchases.copy())
        logger.info("Purchase Added : [ $aPurchases ]")
    }
    else
        logger.info("Purchase Not Added")
}

fun updatePurchase() {
    println("Update Placemark")
    println()
    listPurchases()
    var searchId = getId()
    val aPurchase = search(searchId)

    if(aPurchase != null) {
        print("Enter a new Title for [ " + aPurchase.description + " ] : ")
        aPurchase.description = readLine()!!
        print("Enter a new amount for [ " + aPurchase.amount + " ] : ")
        aPurchase.amount = readLine()!!.toInt()
        println(
            "You updated [ " + aPurchase.description + " ] for description " +
                    "and [ " + aPurchase.amount + " ] for amount"
        )
    }
    else
        println("Purchase Not Updated...")
}

fun listPurchases() {
    println("List All Purchases")
    println()
    purchases.forEach { logger.info("${it}") }
    println()
}

fun searchPurchases() {

    var searchId = getId()
    val aPurchase = search(searchId)

    if(aPurchase != null)
        println("Purchase Details [ $aPurchase ]")
    else
        println("Purchase Not Found...")
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

fun search(id: Long) : PurchasesModel? {
    var foundPurchase: PurchasesModel? = purchases.find { p -> p.id == id }
    return foundPurchase
}

fun dummyData() {
    purchases.add(PurchasesModel(1, "Adidas Jacket", 50))
    purchases.add(PurchasesModel(2, "North Face hat", 20))
    purchases.add(PurchasesModel(3, "Nike Tracksuit", 80))
}
