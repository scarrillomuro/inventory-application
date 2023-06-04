package net.javaguides.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.inventory.entity.Inventory;
import net.javaguides.inventory.repository.InventoryRepository;
import net.javaguides.inventory.service.InventoryService;

/**
 * 
 * @public class InventorySericeImp which implements InventoryService. It
 *         contains the methods for the business logic of Inventory.
 */
@Service
public class InventoryServiceImp implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory saveInventory(Inventory inventory) {
		Inventory existingInventory = inventoryRepository.findByItemNumber(inventory.getItemNumber());
		if (existingInventory == null) {
			// if inventory does not already exist save the new inventory
			return inventoryRepository.save(inventory);
		} else {
			// Inventory was found by item number, so don't add a duplicate
			System.out.println("Duplicate Entry");
			return existingInventory;
		}
	}

	@Override
	public Inventory getInventoryById(Long id) {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		Inventory inventory = null;
		if (optional.isPresent()) {
			inventory = optional.get();
		} else {
			throw new RuntimeException("Inventory not found for id :: " + id);
		}
		return inventory;
	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		Inventory existingInventory = inventoryRepository.findByItemNumber(inventory.getItemNumber());
		if (existingInventory == null) {
			// if inventory item number does not already exist, update inventory
			return inventoryRepository.save(inventory);
		} else {
			// Inventory found by item number, so don't update due to existing duplicate
			System.out.println("Duplicate Entry");
			return existingInventory;
		}

	}

	@Override
	public void deleteInventoryById(Long id) {
		inventoryRepository.deleteById(id);
	}

}
