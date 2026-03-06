package dev.anuradha.omsinventoryservice.service;

import dev.anuradha.omsinventoryservice.dto.PaymentFailedEvent;
import dev.anuradha.omsinventoryservice.entity.Inventory;
import dev.anuradha.omsinventoryservice.repository.InventoryRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryCompensationListener {

    private final InventoryRepository inventoryRepository;

    @SqsListener("${sqs.payment-failed-queue}")
    public void releaseStock(PaymentFailedEvent failedEvent){

        log.warn("Releasing stock for order {}", failedEvent.getOrderId());

        Inventory inventory = inventoryRepository.findByProductId(
                failedEvent.getProductId())
                .orElseThrow();

        inventory.setReservedQty(
                inventory.getReservedQty() - failedEvent.getQuantity()
        );

        inventoryRepository.save(inventory);

        log.info("Stock release for product {}", failedEvent.getProductId());
    }
}
