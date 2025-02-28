package com.poly.demo.enities;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Order_status")
public class Order_statusEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    // Bi-directional many-to-one association to OrderEntity
    @OneToMany(mappedBy="orderStatus")
    private List<OrderEntity> orders;
}
