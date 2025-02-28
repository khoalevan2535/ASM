package com.poly.demo.beans;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.poly.demo.enities.ProductEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscountBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Giá trị chiết khấu không được để trống")
    @Positive(message = "Giá trị chiết khấu phải là số dương")
    @Column(name="Discount_value")
    private BigDecimal discount_value;

    @NotNull(message = "Ngày kết thúc không được để trống")
    @Future(message = "Ngày kết thúc phải trong tương lai")
    @Column(name="End_date")
    private Timestamp end_date;

    @Column(name="Is_active")
    private boolean is_active;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @Future(message = "Ngày bắt đầu phải trong tương lai")
    @Column(name="Start_date")
    private Timestamp start_date;

    //bi-directional many-to-one association to ProductEntity
    @NotNull(message = "Sản phẩm liên kết không được để trống")
    @ManyToOne
    @JoinColumn(name="Product_id")
    private ProductEntity product;
}
