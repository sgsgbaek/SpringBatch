package com.sgbaek.batch.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;
}
