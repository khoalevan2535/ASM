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
@Table(name="Carts")
@NamedQuery(name="CartEntity.findAll", query="SELECT c FROM CartEntity c")
public class CartEntity  {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Created_at")
	private Timestamp created_at;

	//bi-directional many-to-one association to UserEntity
	@ManyToOne
	@JoinColumn(name="User_id")
	private UserEntity user;

	//bi-directional many-to-one association to Carts_ItemEntity
	@OneToMany(mappedBy="cart")
	private List<Carts_ItemEntity> cartsItems;


}