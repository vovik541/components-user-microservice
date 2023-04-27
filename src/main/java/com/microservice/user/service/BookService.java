package com.microservice.user.service;


import com.microservice.user.entity.Book;
import com.microservice.user.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllAccessibleBooks(){
        return bookRepository.findAllByIsBooked(false);
    }

    public List<Book> getAllByName(String bookName){
        return bookRepository.findAllByBookName(bookName);
    }

    public Optional<Book> getById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByUserId(Long userId){
        return bookRepository.findAllByUserId(userId);
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }
}
