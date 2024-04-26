package com.scaler.firstspringapi.dtos;

import com.scaler.firstspringapi.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
