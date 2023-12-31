package com.techpro.project.entity;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "people")
public class People {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @JsonBackReference//fix circle reference
  @OneToMany(mappedBy = "people", cascade = CascadeType.REMOVE)
  @ToString.Exclude // Exclude orderList from toString()
  private List<Order> orderList;

}
