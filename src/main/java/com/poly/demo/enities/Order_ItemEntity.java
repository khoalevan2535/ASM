package com.poly.demo.enities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Order_Items")
@NamedQuery(name="Order_ItemEntity.findAll", query="SELECT o FROM Order_ItemEntity o")
public class Order_ItemEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Price")
	private BigDecimal price;

	@Column(name="Quantity")
	private int quantity;

	@Column(name="Total_price")
	private BigDecimal total_price;

	//bi-directional many-to-one association to OrderEntity
	@ManyToOne
	@JoinColumn(name="Order_id")
	private OrderEntity order;

	//bi-directional many-to-one association to ProductEntity
	@ManyToOne
	@JoinColumn(name="Product_id")
	private ProductEntity product;

	

}