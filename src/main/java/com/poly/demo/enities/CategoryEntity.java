package com.poly.demo.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Categories")
@NamedQuery(name="CategoryEntity.findAll", query="SELECT c FROM CategoryEntity c")
public class CategoryEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="Image")
	private String image;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to ProductEntity
	@OneToMany(mappedBy="category")
    @ToString.Exclude 
	private List<ProductEntity> products;
	

}