package com.poly.demo.beans;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefundBean {

    private int id;

    @NotNull(message = "Số tiền không được để trống")
    @Positive(message = "Số tiền phải lớn hơn 0")
    private BigDecimal amount;

    @NotNull(message = "Ngày tạo không được để trống")
    @PastOrPresent(message = "Ngày tạo không được ở tương lai")
    private Timestamp created_at;

    @Size(max = 255, message = "Lý do không được vượt quá 255 ký tự")
    private String reason;

    @NotNull(message = "Ngày hoàn tiền không được để trống")
    @FutureOrPresent(message = "Ngày hoàn tiền không được ở quá khứ")
    private Timestamp refund_Date;

    private Timestamp updated_at;

    @NotNull(message = "Đơn hàng không được để trống")
    private OrderBean order;

    @NotNull(message = "Người dùng không được để trống")
    private UserBean user;
}
