package com.acm.apirestacm.persistence.entity;


import com.acm.apirestacm.persistence.entity.EmbeddedClass.EnumRol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rol")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnumRol rol;

}
