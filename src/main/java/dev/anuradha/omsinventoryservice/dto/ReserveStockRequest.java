package dev.anuradha.omsinventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ReserveStockRequest {

    @NotNull
    private UUID productId;

    @NotNull
    private Integer quantity;
}
