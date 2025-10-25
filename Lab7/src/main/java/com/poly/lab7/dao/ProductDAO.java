package com.poly.lab7.dao;

import com.poly.lab7.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

//    // Bài 1: Tìm theo khoảng giá
//    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//    List<Product> findByPrice(double minPrice, double maxPrice);
//
//    @Query("FROM Product o WHERE o.name LIKE ?1")
//    Page<Product> findByKeywords(String keywords, Pageable pageable);

    //Bài 4
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

    @Query("SELECT o.category.name AS group, SUM(o.price) AS sum, COUNT(o) AS count "
            + "FROM Product o GROUP BY o.category.name ORDER BY SUM(o.price) DESC")
    java.util.List<com.poly.lab7.model.Report> getInventoryByCategory();

}
