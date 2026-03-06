package dev.anuradha.omsinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PaymentFailedEvent {

    private UUID orderId;
    private UUID productId;
    private Integer quantity;
}
