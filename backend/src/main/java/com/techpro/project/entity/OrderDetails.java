package com.techpro.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_id")
    private Long orderDetailsId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    @Column(name = "quantity")
    private Long quantity;
}
