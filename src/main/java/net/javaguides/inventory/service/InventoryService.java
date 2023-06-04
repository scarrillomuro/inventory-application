package net.javaguides.inventory.service;

import java.util.List;

import net.javaguides.inventory.entity.Inventory;

/**
 * 
 * @public interface InventoryService contains methods for the business logic of
 *         Inventory
 *
 */
public interface InventoryService {

	List<Inventory> getAllInventories();

	Inventory saveInventory(Inventory inventory);

	Inventory getInventoryById(Long id);

	Inventory updateInventory(Inventory inventory);

	void deleteInventoryById(Long id);

}
