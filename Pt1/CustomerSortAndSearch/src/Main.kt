import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.system.exitProcess


fun main() {
    val customers = arrayListOf(
        Customer(1," David Jones", " david.jones@email.com", " 0412345678"),
        Customer(2," Sarah Smith", " sarah.smith@email.com", " 0423456789"),
        Customer(3," Michael Brown", " michael.brown@email.com", " 0434567890" ),
        Customer(4," David Bones", " david.Bones@email.com", " 0412345278" ),
        Customer(5," Indiana Jones", " Indiana.jones@email.com", " 0412345678" ),
    )

    while (true) {
        println("/n===== Customer Sort & Search Menu =====")
        println("1. Show all customers")
        println("2. Sort customers")
        println("3. Search customers")
        println("4. Save customers to binary file")
        println("5. Load customers from binary file")
        println("6. Exit")
        print("Enter your choice: ")

        when (readLine()?.trim()) {
            "1" -> showCustomers(customers)
            "2" -> sortCustomers(customers)
            "3" -> searchCustomers(customers)
            "4" -> saveCustomers(customers)
            "5" -> loadCustomers()
            "6" -> exitProgram()
            else -> println("Invalid option. Please try again.")
        }
    }
}
fun showCustomers(customers: List<Customer>){
    println("\n --- Customer List ---")
    val longString = customers.joinToString(" ")
    println(longString)
}
fun sortCustomers(customers: ArrayList<Customer>) {
    println("\nSorting customers by name...")
    customers.sortBy { it.name }
    println("Customers sorted successfully")
    showCustomers(customers)
}
fun searchCustomers(customers: List<Customer>) {
    print("\nEnter search text (name, email, or mobile): ")
    val query = readLine()?.trim()?.lowercase() ?: ""

    val results = customers.filter {
        it.name.lowercase().contains(query) ||
                it.email.lowercase().contains(query) ||
                it.mobile.contains(query)
    }

    if (results.isEmpty()) {
        println("No customers found.")
    } else {
        println("\n--- SEARCH RESULTS ---")
        results.forEach { println(it) }
    }
}
fun saveCustomers(customers: List<Customer>){
    try {
        ObjectOutputStream(FileOutputStream("customers.dat")).use {
            val customers = null
            it.writeObject(customers)
        }
        println("Customers saved to customers.dat")
    } catch (e: Exception) {
        println("Error saving file: ${e.message}")
    }
}
fun loadCustomers() {
    try {
        val obj = ObjectInputStream(FileInputStream("customers.dat")).use {
            it.readObject()
        }

        if (obj is List<*>) {
            val customers = obj.filterIsInstance<Customer>()
            println("\n--- CUSTOMERS LOADED FROM FILE ---")
            customers.forEach { println(it) }
        } else {
            println("File does not contain a customer list.")
        }

    } catch (e: FileNotFoundException) {
        println("customers.dat not found. Save customers first.")
    } catch (e: Exception) {
        println("Error reading file: ${e.message}")
    }
}

fun exitProgram() {
    println("exiting program. Goodbye")
    exitProcess(0)
}