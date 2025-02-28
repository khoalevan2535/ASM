package com.poly.demo.enities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Images")
@NamedQuery(name="ImageEntity.findAll", query="SELECT i FROM ImageEntity i")
public class ImageEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="Image")
    private String image;

    //bi-directional many-to-one association to ProductEntity
    @ManyToOne
    @JoinColumn(name="Product_id")
    @ToString.Exclude 
    private ProductEntity product;
}