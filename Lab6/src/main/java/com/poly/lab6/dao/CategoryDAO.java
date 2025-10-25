package com.poly.lab6.dao;

import com.poly.lab6.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, String> {}
