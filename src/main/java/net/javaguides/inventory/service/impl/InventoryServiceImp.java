package net.javaguides.inventory.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguides.inventory.entity.Inventory;
import net.javaguides.inventory.repository.InventoryRepository;
import net.javaguides.inventory.service.InventoryService;

@Service
public class InventoryServiceImp implements InventoryService {
	
	
	private InventoryRepository inventoryRepository;

	public InventoryServiceImp(InventoryRepository inventoryRepository) {
		super();
		this.inventoryRepository = inventoryRepository;
	}
	
	@Override
	public List<Inventory> getAllInventories() {
		return inventoryRepository.findAll(); 
	}

	@Override
	public Inventory saveInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public Inventory getInventoryById(Long id) {
		return inventoryRepository.findById(id).get();
	}

	@Override
	public Inventory updateInventory(Inventory inventory) {
		return inventoryRepository.save(inventory);
	}

	@Override
	public void deleteInventoryById(Long id) {
		inventoryRepository.deleteById(id);
	} 

}
