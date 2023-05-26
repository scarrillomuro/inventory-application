package net.javaguides.inventory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.inventory.entity.Inventory;
import net.javaguides.inventory.service.InventoryService;

@Controller
public class InventoryController {
	
	private InventoryService inventoryService;
	
	public InventoryController(InventoryService inventoryService) {
		super();
		this.inventoryService = inventoryService;
	}
		
	@GetMapping("/index")
	public String viewHomePage(Model model) {
		model.addAttribute("inventory", inventoryService.getAllInventories());
		return "index"; 
	}
	@GetMapping("/index/new")
	public String createInventoryForm(Model model) {
		
		// create inventory object to hold new inventory form data
		Inventory inventory = new Inventory(); 
		model.addAttribute("inventory", inventory); 
		return "create_inventory";
	}
	
	@PostMapping("/index")
	public String saveInventory(@ModelAttribute("inventory") Inventory inventory){
		inventoryService.saveInventory(inventory);
		return "redirect:/index"; 		
	}
	
	@GetMapping("/index/edit/{id}")
	public String editInventoryForm(@PathVariable Long id, Model model) {
		model.addAttribute("inventory", inventoryService.getInventoryById(id)); 
		return "edit_inventory"; 
	}
	
	// create method to handle edit request 
	
	@PostMapping("/index/{id}")
	public String updateInventory(@PathVariable Long id, 
			@ModelAttribute("inventory") Inventory inventory,
			Model model){
		
		// get inventory from database by id 
		Inventory existingInventory = inventoryService.getInventoryById(id); 
		existingInventory.setId(id); 
		existingInventory.setItemNumber(inventory.getItemNumber()); 
		existingInventory.setItem(inventory.getItem()); 
		existingInventory.setCost(inventory.getCost()); 
		existingInventory.setUnitOfMeasure(inventory.getUnitOfMeasure()); 
		existingInventory.setInStock(inventory.getInStock()); 
		existingInventory.setParLevel(inventory.getParLevel()); 
		existingInventory.setToBeOrdered(inventory.getToBeOrdered()); 
		existingInventory.setTotalCost(inventory.getTotalCost()); 
		existingInventory.setDescription(inventory.getDescription()); 
		
		
		// save updated inventory object
		inventoryService.updateInventory(existingInventory);
		return "redirect:/index"; 
		
	}
	
	// handler method to delete inventory request
	@GetMapping("/index/{id}")
	public String deleteInventory(@PathVariable Long id) {
		inventoryService.deleteInventoryById(id); 
		return "redirect:/index"; 
	}
}
