package com.techpro.project.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long itemId;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
  @ToString.Exclude // Exclude orderList from toString()
  @JsonBackReference//this helps with the deletion of FK * If we don't have this when deleting the child, parent throws FK violation
  private List<OrderDetails> orderDetails;

}
