package com.example.retailer.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private long id;
    @NotBlank
    private String customerName;
    @Min(1)
    @Max(12)
    @NonNull
    private Integer month;
    @Min(0)
    @NonNull
    private Integer amount;

}
