package com.poly.demo.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductBean {

    private int id;

    @NotNull(message = "Ngày tạo không được để trống")
    private Timestamp created_at;

    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 255, message = "Mô tả không được dài hơn 255 ký tự")
    private String description;

    @NotNull(message = "Trạng thái không được để trống")
    private boolean is_Active;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 100, message = "Tên sản phẩm không được dài hơn 100 ký tự")
    private String name;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private BigDecimal price;

    @NotNull(message = "Số lượng trong kho không được để trống")
    @Positive(message = "Số lượng phải lớn hơn hoặc bằng 0")
    private int stock_quantity;

    private List<Carts_ItemBean> cartsItems;

    private List<DiscountBean> discounts;

    private List<ImageBean> images;

    private List<Order_ItemBean> orderItems;

    @NotNull(message = "Danh mục không được để trống")
    private CategoryBean category;

    private List<WarehousBean> warehouses;
}
