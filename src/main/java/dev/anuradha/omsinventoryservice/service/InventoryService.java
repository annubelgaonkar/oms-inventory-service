package dev.anuradha.omsinventoryservice.service;

import dev.anuradha.omsinventoryservice.dto.ReserveStockRequest;
import dev.anuradha.omsinventoryservice.dto.StockRequest;
import dev.anuradha.omsinventoryservice.entity.Inventory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface InventoryService {

    Inventory addStock(StockRequest request);
    boolean reserveStock(ReserveStockRequest reserveStockRequest);
    Integer getAvailableStock(UUID productId);

}
