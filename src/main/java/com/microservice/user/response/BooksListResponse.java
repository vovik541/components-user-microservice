package com.microservice.user.response;

import com.microservice.user.entity.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BooksListResponse {

    private List<BookDTO> books;


}