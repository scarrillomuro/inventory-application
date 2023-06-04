package net.javaguides.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @public class Inventory contain private fields with setter and getters that
 *         hold information about the inventory such as; Id, item number, item,
 *         cost, unit of measure, in stock, par level, to be ordered and total
 *         cost.
 *
 */

@Entity
@Table(name = "inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "item_number", nullable = false)
	private String itemNumber;

	@Column(name = "item")
	private String item;

	@Column(name = "cost")
	private double cost;

	@Column(name = "unit_of_measure")
	private String unitOfMeasure;

	@Column(name = "in_stock")
	private double inStock;

	@Column(name = "par_level")
	private double parLevel;

	@Column(name = "to_be_ordered")
	private double toBeOrdered;

	@Column(name = "total_cost")
	private double totalCost;

	@Column(name = "description")
	private String description;

	public Inventory() {

	}

	public Inventory(String itemNumber, String item, double cost, String unitOfMeasure, double inStock, double parLevel,
			double toBeOrdered, double totalCost, String description) {
		super();
		this.itemNumber = itemNumber;
		this.item = item;
		this.cost = cost;
		this.unitOfMeasure = unitOfMeasure;
		this.inStock = inStock;
		this.parLevel = parLevel;
		this.toBeOrdered = toBeOrdered;
		this.totalCost = totalCost;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getInStock() {
		return inStock;
	}

	public void setInStock(double inStock) {
		this.inStock = inStock;
	}

	public double getParLevel() {
		return parLevel;
	}

	public void setParLevel(double parLevel) {
		this.parLevel = parLevel;
	}

	public double getToBeOrdered() {
		if (parLevel == inStock) {
			toBeOrdered = (parLevel - inStock);
			return toBeOrdered;
		} else if (inStock > parLevel) {
			toBeOrdered = 0;
			return toBeOrdered;
		} else {
			toBeOrdered = parLevel - inStock;
			return toBeOrdered;
		}
	}

	public void setToBeOrdered(double toBeOrdered) {
		this.toBeOrdered = toBeOrdered;
	}

	public double getTotalCost() {
		double totalCost = toBeOrdered * cost;
		return Double.parseDouble(String.format("%.2f", totalCost));
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
