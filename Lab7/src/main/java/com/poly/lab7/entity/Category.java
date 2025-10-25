package com.poly.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @Column(length = 4)
    private String id;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
