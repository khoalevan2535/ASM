package com.poly.demo.enities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Carts_Items")
@NamedQuery(name="Carts_ItemEntity.findAll", query="SELECT c FROM Carts_ItemEntity c")
public class Carts_ItemEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Created_at")
	private Timestamp created_at;

	@Column(name="Quantity")
	private int quantity;

	//bi-directional many-to-one association to CartEntity
	@ManyToOne
	@JoinColumn(name="Cart_id")
	private CartEntity cart;

	//bi-directional many-to-one association to ProductEntity
	@ManyToOne
	@JoinColumn(name="Product_id")
	private ProductEntity product;

	
}