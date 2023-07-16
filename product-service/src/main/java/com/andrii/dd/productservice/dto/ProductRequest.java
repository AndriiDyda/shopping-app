package com.andrii.dd.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
