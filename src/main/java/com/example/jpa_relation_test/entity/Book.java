package com.example.jpa_relation_test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Integer price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "book_store_id")
    private BookStore bookStore;

    @OneToMany(mappedBy = "book")
    private List<Purchase> purchases = new ArrayList<>();

    public Book(String title, String author, Integer price, Integer stock ){
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;

    }
}
