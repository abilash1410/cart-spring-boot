package com.ecommerce.cart.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@Entity//Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //to autonumber the ID attribute we use this
    private int id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String brand;
    private String model;
    private String description;
    private String category;
    private boolean available;
    @JsonFormat (shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private String imageName;
    private String imageType;
    private byte[] imageData;


   /* "name":"dell laptop",
    "price":30000,
    "quantity":4,
    "brand":"dell",
    "model":"Aspire Lite",
    "description":"A dell laptop",
    "category":"computer",
    "available":true,
    "releaseDate":"19-09-2024"
    */




}
