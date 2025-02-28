package com.poly.demo.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Products")
@NamedQuery(name="ProductEntity.findAll", query="SELECT p FROM ProductEntity p")
public class ProductEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Created_at")
	private Timestamp created_at;

	@Column(name="Description")
	private String description;

	@Column(name="Is_Active")
	private boolean is_Active;

	@Column(name="Name")
	private String name;

	@Column(name="Price")
	private BigDecimal price;

	@Column(name="Stock_quantity")
	private int stock_quantity;

	//bi-directional many-to-one association to Carts_ItemEntity
	@OneToMany(mappedBy="product")
	private List<Carts_ItemEntity> cartsItems;

	//bi-directional many-to-one association to DiscountEntity
	@OneToMany(mappedBy="product")
	private List<DiscountEntity> discounts;

	//bi-directional many-to-one association to ImageEntity
	@OneToMany(mappedBy="product")
	private List<ImageEntity> images;

	//bi-directional many-to-one association to Order_ItemEntity
	@OneToMany(mappedBy="product")
	private List<Order_ItemEntity> orderItems;

	//bi-directional many-to-one association to CategoryEntity
	@ManyToOne
	@JoinColumn(name="Category_id")
	private CategoryEntity category;

	//bi-directional many-to-one association to WarehousEntity
	@OneToMany(mappedBy="product")
	private List<WarehousEntity> warehouses;

	

}