package com.techpro.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name="order_date")
    private Date orderDate;

    //Many orders to onePerson
    @ManyToOne
    @JoinColumn(name = "person_id")
    private People people;


    @JsonBackReference
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.REMOVE)
    private List<OrderDetails> orderDetails;

}
