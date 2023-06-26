package com.techpro.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@EqualsAndHashCode(exclude = "people")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long orderId;
  @Column(name = "order_date")
  private Date orderDate;
  @ManyToOne
  @JoinColumn(name = "person_id")
  private People people;

  @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
  @ToString.Exclude // Exclude orderList from toString()
  @JsonBackReference//this helps with the deletion of FK * If we don't have this when deleting the child, parent throws FK violation
  private List<OrderDetails> orderDetails;

  public Order(Date orderDate, People people) {
    this.orderDate = orderDate;
    this.people = people;
  }
}
