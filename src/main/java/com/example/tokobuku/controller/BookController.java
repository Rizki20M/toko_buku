package com.example.tokobuku.controller;

import com.example.tokobuku.entity.Book;
import com.example.tokobuku.exception.BookNotFoundException;
import com.example.tokobuku.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/books")
    List<Book> findAll(){
        return bookRepository.findAll();
    }
    @GetMapping("/books/{id}")
    Book cariBuku(@PathVariable @Min(1) Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException(id));
    }
    @PostMapping ("/books")
    Book newBook(@Valid @RequestBody Book book){
        return bookRepository.save(book);
    }
}
