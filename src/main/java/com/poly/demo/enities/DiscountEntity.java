package com.poly.demo.enities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Discount")
@NamedQuery(name="DiscountEntity.findAll", query="SELECT d FROM DiscountEntity d")
public class DiscountEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="Discount_value")
    private BigDecimal discountValue; // đổi thành camelCase

    @Column(name="End_date")
    private Timestamp endDate; // đổi thành camelCase

    @Column(name="Is_active")
    private boolean isActive;

    @Column(name="Start_date")
    private Timestamp startDate; // đổi thành camelCase

    // Bi-directional many-to-one association to ProductEntity
    @ManyToOne
    @JoinColumn(name="Product_id")
    private ProductEntity product;
}
