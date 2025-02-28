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
@Table(name="Payments")
@NamedQuery(name="PaymentEntity.findAll", query="SELECT p FROM PaymentEntity p")
public class PaymentEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Amount")
	private BigDecimal amount;

	@Column(name="Payment_date")
	private Timestamp payment_date;

	@Column(name="Transaction_id")
	private String transactionId;

	//bi-directional many-to-one association to OrderEntity
	@ManyToOne
	@JoinColumn(name="Order_id")
	private OrderEntity order;

	//bi-directional many-to-one association to Payment_methodEntity
	@ManyToOne
	@JoinColumn(name="Payment_methods_id")
	private Payment_methodEntity paymentMethod;

	//bi-directional many-to-one association to Payment_statusEntity
	@ManyToOne
	@JoinColumn(name="Payment_status_id")
	private Payment_statusEntity paymentStatus;

	

}