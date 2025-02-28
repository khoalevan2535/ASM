package com.poly.demo.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
public class PaymentBean {

    private int id;

    @NotNull(message = "Số tiền không được để trống")
    @Positive(message = "Số tiền phải lớn hơn 0")
    private BigDecimal amount;

    @NotNull(message = "Ngày thanh toán không được để trống")
    private Timestamp payment_date;

    @NotBlank(message = "Mã giao dịch không được để trống")
    @Size(max = 50, message = "Mã giao dịch không được dài hơn 50 ký tự")
    private String transaction_id;

    @NotNull(message = "Đơn hàng không được để trống")
    private Integer orderId;

    @NotNull(message = "Phương thức thanh toán không được để trống")
    private Integer paymentMethodId;

    @NotNull(message = "Trạng thái thanh toán không được để trống")
    private Integer paymentStatusId;
    
    private Timestamp orderDate;
    
    public String isPaymentDateError() {
        if (payment_date == null) {
            return "Ngày thanh toán không được để trống";
        }
        if (orderDate != null && payment_date.before(orderDate)) {
            return "Ngày thanh toán phải sau ngày đặt hàng";
        }
        return null;
    }

}
