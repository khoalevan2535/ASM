package com.poly.demo.enities;



import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Payment_status")
@NamedQuery(name = "Payment_statusEntity.findAll", query = "SELECT p FROM Payment_statusEntity p")
public class Payment_statusEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 100) 
    private String name;

//    @OneToMany(mappedBy = "paymentStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<PaymentEntity> payments;
}
