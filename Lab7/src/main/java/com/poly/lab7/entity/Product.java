package com.poly.lab7.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false)
    private String name;

    private String image;
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate")
    private Date createDate;

    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;
}
