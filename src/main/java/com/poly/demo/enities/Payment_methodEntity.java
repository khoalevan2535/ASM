package com.poly.demo.enities;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Payment_methods")
@NamedQuery(name="Payment_methodEntity.findAll", query="SELECT p FROM Payment_methodEntity p")
public class Payment_methodEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Description")
	private String description;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to PaymentEntity
	@OneToMany(mappedBy="paymentMethod")
	private List<PaymentEntity> payments;

	

}