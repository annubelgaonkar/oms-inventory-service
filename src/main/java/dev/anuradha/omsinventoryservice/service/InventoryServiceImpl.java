package dev.anuradha.omsinventoryservice.service;

import dev.anuradha.omsinventoryservice.dto.ReserveStockRequest;
import dev.anuradha.omsinventoryservice.dto.StockRequest;
import dev.anuradha.omsinventoryservice.entity.Inventory;
import dev.anuradha.omsinventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public Inventory addStock(StockRequest request) {

        Inventory inventory = inventoryRepository.findByProductId(
                        request.getProductId())
                .orElse(Inventory.builder()
                        .productId(request.getProductId())
                        .availableQty(0)
                        .reservedQty(0)
                        .build());

        inventory.setAvailableQty(
                inventory.getAvailableQty() + request.getQuantity()
        );

        return inventoryRepository.save(inventory);
    }

    @Transactional
    @Override
    public boolean reserveStock(ReserveStockRequest reserveStockRequest) {

        Inventory inventory = inventoryRepository.findByProductId(reserveStockRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        int available = inventory.getAvailableQty() - inventory.getReservedQty();

        if(available < reserveStockRequest.getQuantity()){
            log.warn("Insufficient stock for product {}", reserveStockRequest.getProductId());
            return false;
        }

        inventory.setReservedQty(inventory.getReservedQty() + reserveStockRequest.getQuantity());

        inventoryRepository.save(inventory);
        return true;

    }

    @Override
    public Integer getAvailableStock(UUID productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        return inventory.getAvailableQty() - inventory.getReservedQty();

    }
}
