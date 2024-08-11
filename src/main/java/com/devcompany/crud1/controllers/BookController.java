package com.devcompany.crud1.controllers;

import com.devcompany.crud1.entities.Book;
import com.devcompany.crud1.services.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        try {
            Book savedBook = bookServiceImpl.saveBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        try {
            Book savedBook = bookServiceImpl.updateBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        return new ResponseEntity<>(bookServiceImpl.getBooks(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if(book.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        Optional<Book> book = bookServiceImpl.getBookById(id);
        if (book.isEmpty()){
            bookServiceImpl.deleteBook(book.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
