package com.scaler.firstspringapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private String desc;
    private double price;
    private String image;
    private Category category;
}
