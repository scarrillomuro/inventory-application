package net.javaguides.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.inventory.entity.Inventory;
import net.javaguides.inventory.service.InventoryService;

/**
 * 
 * @author Samuel Carrillo
 * @public class InventoryController provides the methods to interact with the
 *         database to either read or write data to the database. It is also
 *         used to retrieve data to render a view template, or redirect to a
 *         different page.
 *
 */
@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	/**
	 * @method viewHomePage is design to present list of inventories.
	 * @param model
	 * @return It returns user back to "index" which is a Thymeleaf template with
	 *         list of inventory.
	 */
	// display list of appointment
	@GetMapping("/index")
	public String viewHomePage(Model model) {
		model.addAttribute("inventory", inventoryService.getAllInventories());
		return "index";
	}

	/**
	 * @method createInventoryForm show a form that with fields to input information
	 *         for new inventory.
	 * @param model
	 * @return A Thymeleaf template with fields to create new inventory.
	 */
	@GetMapping("/index/new")
	public String createInventoryForm(Model model) {

		// create inventory object to hold new inventory form data
		Inventory inventory = new Inventory();
		model.addAttribute("inventory", inventory);
		return "create_inventory";
	}

	/**
	 * @method saveInventory saves new inventory to database and checks for
	 *         duplicate entries.
	 * @param inventory
	 * @return redirects back to list of inventory page "index" Thymeleaf template
	 */
	@PostMapping("/index")
	public String saveInventory(@ModelAttribute("inventory") Inventory inventory) {
		try {
			inventoryService.saveInventory(inventory);
			return "redirect:/index";
		} catch (DataIntegrityViolationException e) {
			System.out.print("Duplicate Entry");
		}
		return "redirect:/index";
	}

	/**
	 * @method editInventoryForm brings out the information regarding inventory to
	 *         be edited.
	 * @param id
	 * @param model
	 * @return Thymeleaf template that takes user to "edit_inventory" to update
	 *         inventory
	 */

	@GetMapping("/index/edit/{id}")
	public String editInventoryForm(@PathVariable Long id, Model model) {
		model.addAttribute("inventory", inventoryService.getInventoryById(id));
		return "edit_inventory";
	}

	/**
	 * @method updateInventory handles the edit request of inventory.
	 * @param id
	 * @param inventory
	 * @param model
	 * @return redirects back to list of inventory "index" Thymeleaf template
	 */
	// create method to handle edit request
	@PostMapping("/index/{id}")
	public String updateInventory(@PathVariable Long id, @ModelAttribute("inventory") Inventory inventory,
			Model model) {

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

	/**
	 * @method delete inventory handles the deleting of inventory
	 * @param id
	 * @return redirects back to index thymeleaf template
	 */
	// handler method to delete inventory request
	@GetMapping("/index/{id}")
	public String deleteInventory(@PathVariable Long id) {
		inventoryService.deleteInventoryById(id);
		return "redirect:/index";
	}
}
