package com.devcompany.crud1.services;

import com.devcompany.crud1.BookService;
import com.devcompany.crud1.entities.Book;
import com.devcompany.crud1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
