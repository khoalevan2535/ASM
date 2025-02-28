package com.poly.demo.enities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Warehouses")
@NamedQuery(name="WarehousEntity.findAll", query="SELECT w FROM WarehousEntity w")
public class WarehousEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Quantity")
	private int quantity;

	//bi-directional many-to-one association to ProductEntity
	@ManyToOne
	@JoinColumn(name="Product_id")
	private ProductEntity product;

	//bi-directional many-to-one association to UserEntity
	@ManyToOne
	@JoinColumn(name="User_id")
	private UserEntity user;

	
}