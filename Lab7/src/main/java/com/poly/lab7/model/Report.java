package com.poly.lab7.model;

import java.io.Serializable;

public interface Report {
    Serializable getGroup(); // category
    Double getSum();          // tổng giá
    Long getCount();          // số sản phẩm
}
