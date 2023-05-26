package net.javaguides.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
