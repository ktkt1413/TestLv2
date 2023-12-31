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
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "bookStore")
    private List<Book> bookList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return bookList;
    }

    public void addBook(Book book){
        this.bookList.add(book);
    }
}
