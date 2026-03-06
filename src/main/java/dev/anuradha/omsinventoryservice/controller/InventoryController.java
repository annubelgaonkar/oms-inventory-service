package dev.anuradha.omsinventoryservice.controller;

import dev.anuradha.omsinventoryservice.dto.ReserveStockRequest;
import dev.anuradha.omsinventoryservice.dto.StockRequest;
import dev.anuradha.omsinventoryservice.entity.Inventory;
import dev.anuradha.omsinventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<Inventory> addStock(@RequestBody StockRequest request){

        return ResponseEntity.ok().body(inventoryService.addStock(request));
    }

    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveStock(@RequestBody ReserveStockRequest reserveStockRequest){

        return ResponseEntity.ok().body(inventoryService.reserveStock(reserveStockRequest));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Integer> getAvailable(@PathVariable UUID productId){
        return ResponseEntity.ok().body(inventoryService.getAvailableStock(productId));
    }
}
