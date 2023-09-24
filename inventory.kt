// Define a class to represent items in the game with a type classification
data class GameItem(val id: Int, val name: String, val itemType: ItemType, var quantity: Int)

// Define an enum for item types
enum class ItemType { WEAPON, ARMOR, ITEM }

// Create a class to manage the player's inventory
class PlayerInventory(private val maxCapacity: Int) {
    // Use lists to store game items in separate compartments
    private val weapons = mutableListOf<GameItem>()
    private val armor = mutableListOf<GameItem>()
    private val items = mutableListOf<GameItem>()

    // Method to add a new item to the inventory and assign it to the correct compartment based on its type
    fun addItem(item: GameItem) {
        if (isInventoryFull()) {
            println("Inventory is full. Cannot add ${item.name}.")
            return
        }

        when (item.itemType) {
            ItemType.WEAPON -> weapons.add(item)
            ItemType.ARMOR -> armor.add(item)
            ItemType.ITEM -> items.add(item)
        }
    }

    // Method to remove an item from the inventory by ID
    fun removeItemById(id: Int) {
        val removedItem = weapons.find { it.id == id }
            ?: armor.find { it.id == id }
            ?: items.find { it.id == id }

        if (removedItem != null) {
            when (removedItem.itemType) {
                ItemType.WEAPON -> weapons.remove(removedItem)
                ItemType.ARMOR -> armor.remove(removedItem)
                ItemType.ITEM -> items.remove(removedItem)
            }
        }
    }

    // Method to update the quantity of an item
    fun updateItemQuantity(id: Int, newQuantity: Int) {
        val item = weapons.find { it.id == id }
            ?: armor.find { it.id == id }
            ?: items.find { it.id == id }

        if (item != null) {
            item.quantity = newQuantity
        }
    }

    // Method to list all items in a specific compartment
    fun listItemsInCompartment(compartment: String) {
        println("$compartment:")
        when (compartment) {
            "weapons" -> weapons.forEach { println("${it.name} (ID: ${it.id}) - Quantity: ${it.quantity}") }
            "armor" -> armor.forEach { println("${it.name} (ID: ${it.id}) - Quantity: ${it.quantity}") }
            "items" -> items.forEach { println("${it.name} (ID: ${it.id}) - Quantity: ${it.quantity}") }
            else -> println("Invalid compartment.")
        }
    }

    // Helper method to check if the inventory is full
    private fun isInventoryFull(): Boolean {
        return weapons.size + armor.size + items.size >= maxCapacity
    }
}

fun main() {
    // Create an instance of the PlayerInventory class with a maximum capacity of 10
    val playerInventory = PlayerInventory(10)

    // Add some items to the player's inventory with automatic type detection
    playerInventory.addItem(GameItem(1, "Sword", ItemType.WEAPON, 1))
    playerInventory.addItem(GameItem(2, "Shield", ItemType.ARMOR, 1))
    playerInventory.addItem(GameItem(3, "Health Potion", ItemType.ITEM, 5))
    playerInventory.addItem(GameItem(4, "Bow", ItemType.WEAPON, 1))

    // List all items in each compartment
    playerInventory.listItemsInCompartment("weapons")
    playerInventory.listItemsInCompartment("armor")
    playerInventory.listItemsInCompartment("items")

    // Remove an item from the player's inventory
    playerInventory.removeItemById(2)

    // List items in the armor compartment after removal
    playerInventory.listItemsInCompartment("armor")

    // Update the quantity of an item
    playerInventory.updateItemQuantity(1, 2)

    // List items in the weapons compartment after updating quantity
    playerInventory.listItemsInCompartment("weapons")
}
