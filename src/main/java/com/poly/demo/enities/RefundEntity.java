package com.poly.demo.enities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Refunds")
@NamedQuery(name="RefundEntity.findAll", query="SELECT r FROM RefundEntity r")
public class RefundEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="Amount")
    private BigDecimal amount;

    @Column(name="Created_at") // Giữ nguyên tên cột trong DB
    private Timestamp createdAt; // Sửa tên thuộc tính thành createdAt

    @Column(name="Reason")
    private String reason;

    @Column(name="Refund_Date")
    private Timestamp refundDate; // Đổi tên để phù hợp với camelCase

    @Column(name="Updated_at")
    private Timestamp updatedAt; // Đổi tên để phù hợp với camelCase

    //bi-directional many-to-one association to OrderEntity
    @ManyToOne
    @JoinColumn(name="Order_id")
    private OrderEntity order;

    //bi-directional many-to-one association to UserEntity
    @ManyToOne
    @JoinColumn(name="User_id")
    private UserEntity user;
}
