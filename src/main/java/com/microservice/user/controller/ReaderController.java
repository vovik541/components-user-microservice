package com.microservice.user.controller;

import com.microservice.user.entity.Book;
import com.microservice.user.entity.User;
import com.microservice.user.entity.dto.BookDTO;
import com.microservice.user.mapper.BookMapper;
import com.microservice.user.mapper.UserMapper;
import com.microservice.user.repository.UserRepository;
import com.microservice.user.request.GetBookRequest;
import com.microservice.user.response.BooksListResponse;
import com.microservice.user.response.UsersListResponse;
import com.microservice.user.service.BookService;
import com.microservice.user.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class ReaderController {
    private final ReaderService readerService;
    private final BookService bookService;

    private final UserRepository userRepository;

    private final BookMapper bookMapper;
    private final UserMapper userMapper;
    @GetMapping("/profile/{id}")
    public @ResponseBody BooksListResponse readerMainPage(@PathVariable("id") Long userId) {
        List<Book> books = bookService.getBooksByUserId(userId);
        return new BooksListResponse(bookMapper.booksToBookDTOs(books));
    }

    @GetMapping("/search/{name}")
    public @ResponseBody BooksListResponse findBookPage(@PathVariable("name") String name) {
        List<BookDTO> books = bookMapper.booksToBookDTOs(bookService.getAllByName(name));
        return new BooksListResponse(books);
    }

    @GetMapping("/find")
    public @ResponseBody BooksListResponse findBookPage() {
        List<Book> books = bookService.getAllAccessibleBooks();
        return new BooksListResponse(bookMapper.booksToBookDTOs(books));
    }

    @PostMapping("/take_book")
    public @ResponseBody BookDTO updateBook(@RequestBody GetBookRequest request) {
        User user = userRepository.findByLogin(request.getUserName());
        Book book = bookService.getById(request.getId()).get();
        book.setUserId(user.getId());
        book.setBooked(true);
        bookService.updateBook(book);

        return bookMapper.bookToBookDTO(book);
    }

    @GetMapping("/get_book")
    public @ResponseBody BookDTO get_book(@RequestBody GetBookRequest request) {
        Book book = bookService.getById(request.getId()).get();
        return bookMapper.bookToBookDTO(book);
    }

    @PostMapping("/give_back/{id}")
    public @ResponseBody void deleteBook(@PathVariable(name = "id") Long id) {
        Book book = bookService.getById(id).get();
        book.setBooked(false);
        book.setUserId(null);
        bookService.updateBook(book);
    }

    @GetMapping("/all")
    public @ResponseBody UsersListResponse getAllReaders() {
        List<User> allReaders = readerService.findAllReaders();
        return new UsersListResponse(userMapper.usersToUserDTOs(allReaders));
    }

    @GetMapping("find/{id}")
    public @ResponseBody ResponseEntity<User> getReaderById(@PathVariable("id") Long id) {
        User user = readerService.findReaderById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<User> addReader(@RequestBody User user) {
        User newUser = readerService.addReader(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("/update")
    public @ResponseBody ResponseEntity<User> updateEmployee(@RequestBody User employee) {
        User updateEmployee = readerService.updateReader(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> deleteReader(@PathVariable("id") Long id) {
        readerService.deleteReader(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}