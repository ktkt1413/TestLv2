package com.example.jpa_relation_test.controller;

import com.example.jpa_relation_test.dto.BookUpdateRequestDto;
import com.example.jpa_relation_test.entity.Book;
import com.example.jpa_relation_test.entity.Member;
import com.example.jpa_relation_test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @PostMapping("/member")
    public void signup(@RequestBody Member member) {
        testService.signup(member);
    }

    @GetMapping("/member")
    public List<Member> findAllMember() {
        return testService.findAllMember();
    }

    @PutMapping("/bookstore/{bookStoreId}/book/{bookId}")
    public List<Book> updateBook(@RequestBody BookUpdateRequestDto requestDto,
                           @PathVariable("bookStoreId") Long bookStoreId,
                           @PathVariable("bookId") Long bookId) {
        testService.updateBook(bookStoreId, bookId, requestDto.getPrice(), requestDto.getStock());
        return testService.findBook(bookStoreId);
    }

    @GetMapping("/bookstore/{bookStoreId}/book")
    public List<Book> findBook(@PathVariable("bookStoreId") Long bookStoreId) {
        return testService.findBook(bookStoreId);
    }

    @PostMapping("/bookstore/{bookStoreId}/book/{bookId}")
    public List<Book> transferBook(@PathVariable("bookId") Long bookId,
                             @PathVariable("bookStoreId") Long bookStoreId) {
        testService.transferBook(bookId, bookStoreId);
        return testService.findBook(bookStoreId);
    }


}
