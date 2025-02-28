package com.poly.demo.enities;

import java.io.Serializable;
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
@Table(name="Orders")
@NamedQuery(name="OrderEntity.findAll", query="SELECT o FROM OrderEntity o")
public class OrderEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Created_at")
	private Timestamp created_at;

	@Column(name="Shipping_fee")
	private BigDecimal shipping_fee;

	//bi-directional many-to-one association to Order_ItemEntity
	@OneToMany(mappedBy="order")
	private List<Order_ItemEntity> orderItems;

	//bi-directional many-to-one association to AddressEntity
	@ManyToOne
	@JoinColumn(name="Address_id")
	private AddressEntity address;

	//bi-directional many-to-one association to Order_statusEntity
	@ManyToOne
	@JoinColumn(name="Status_id")
	private Order_statusEntity orderStatus;

	//bi-directional many-to-one association to UserEntity
	@ManyToOne
	@JoinColumn(name="User_id")
	private UserEntity user;

	//bi-directional many-to-one association to PaymentEntity
	@OneToMany(mappedBy="order")
	private List<PaymentEntity> payments;

	//bi-directional many-to-one association to RefundEntity
	@OneToMany(mappedBy="order")
	private List<RefundEntity> refunds;

	
}