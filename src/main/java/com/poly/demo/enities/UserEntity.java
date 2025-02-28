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
@Table(name="Users")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Avatar")
	private String avatar;

	@Column(name="Created_at")
	private Timestamp created_at;

	@Column(name="Email")
	private String email;

	@Column(name="Is_Active")
	private boolean is_Active;

	@Column(name="Name")
	private String name;

	@Column(name="password_hash")
	private String passwordHash;

	@Column(name="Role")
	private int role;

	@Column(name="Updated_at")
	private Timestamp updated_at;

	@Column(name="Username")
	private String username;

	//bi-directional many-to-one association to AddressEntity
	@OneToMany(mappedBy="user")
	private List<AddressEntity> addresses;

	//bi-directional many-to-one association to CartEntity
	@OneToMany(mappedBy="user")
	private List<CartEntity> carts;

	//bi-directional many-to-one association to OrderEntity
	@OneToMany(mappedBy="user")
	private List<OrderEntity> orders;

	//bi-directional many-to-one association to RefundEntity
	@OneToMany(mappedBy="user")
	private List<RefundEntity> refunds;

	//bi-directional many-to-one association to WarehousEntity
	@OneToMany(mappedBy="user")
	private List<WarehousEntity> warehouses;

	

}