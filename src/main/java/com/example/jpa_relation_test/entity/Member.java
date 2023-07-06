package com.example.jpa_relation_test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String nickname;

    @ManyToOne
    private BookStore bookStore;

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Purchase> purchases = new ArrayList<>();

    public Member(String email, String password, String address, String phoneNumber, String nickname) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }
}
