package com.example.jpa_relation_test.service;

import com.example.jpa_relation_test.entity.Book;
import com.example.jpa_relation_test.entity.BookStore;
import com.example.jpa_relation_test.entity.Member;
import com.example.jpa_relation_test.repository.BookRepository;
import com.example.jpa_relation_test.repository.BookStoreRepository;
import com.example.jpa_relation_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final BookStoreRepository bookStoreRepository;

    @Transactional
    public void signup(Member member) {
        memberRepository.save(member);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    @Transactional
    public void updateBook(Long bookStoreId, Long bookId, int price, int stock) {
        BookStore bookStore = getBookStoreById(bookStoreId);
        Book book = getBookById(bookId);

        book.setPrice(price);
        book.setStock(stock);

        moveBook(book, bookStore);
    }

    @Transactional
    public List<Book> findBook(Long bookStoreId) {
        return bookRepository.findByBookStoreId(bookStoreId);
    }

    public void transferBook(Long bookId, Long bookStoreId) {
        Book book = getBookById(bookId);
        BookStore destinationBookStore = getBookStoreById(bookStoreId);

        BookStore currentBookStore = book.getBookStore();
        currentBookStore.getBooks().remove(book);

        moveBook(book, destinationBookStore);
    }


    private BookStore getBookStoreById(Long bookStoreId) {
        return bookStoreRepository.findById(bookStoreId)
                .orElseThrow(() -> new IllegalArgumentException("해당 지점을 찾을 수 없습니다"));
    }

    private Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 책을 찾을 수 없습니다"));
    }

    private void moveBook(Book book, BookStore bookStore) {
        book.setBookStore(bookStore);
        bookStore.getBooks().add(book);
    }




//    public void transferBook(Long bookId, Long bookStoreId) {
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow();
//        BookStore bookStore = bookStoreRepository.findById(bookStoreId)
//                .orElseThrow();
//        bookStore.addBook(book);
//    }
}
