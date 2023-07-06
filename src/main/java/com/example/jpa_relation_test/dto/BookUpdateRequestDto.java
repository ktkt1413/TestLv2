package com.example.jpa_relation_test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookUpdateRequestDto {
    private int price;
    private int stock;
}

