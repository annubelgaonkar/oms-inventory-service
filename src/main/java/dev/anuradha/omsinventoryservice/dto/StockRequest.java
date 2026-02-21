package dev.anuradha.omsinventoryservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class StockRequest {

    @NotNull
    private UUID productId;

    @NotNull
    private Integer quantity;

}
