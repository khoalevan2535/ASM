package com.poly.demo.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Addresses")
@NamedQuery(name="AddressEntity.findAll", query="SELECT a FROM AddressEntity a")
public class AddressEntity  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Address")
	private String address;

	@Column(name="Address_type")
	private String address_type;

	@Column(name="Name")
	private String name;

	@Column(name="Phone")
	private String phone;

	//bi-directional many-to-one association to UserEntity
	@ManyToOne
	@JoinColumn(name="User_id")
	private UserEntity user;

	//bi-directional many-to-one association to OrderEntity
	@OneToMany(mappedBy="address")
	private List<OrderEntity> orders;





}