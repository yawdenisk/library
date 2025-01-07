package com.example.library.Services;

import com.example.library.Entities.Author;
import com.example.library.Entities.Book;
import com.example.library.Handler.BookNotFoundException;
import com.example.library.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Optional<Book> getBook(UUID id){
        return bookRepository.findById(id);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }

    public void updateBook(UUID id, Book book) {
        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException());
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setDescription(book.getDescription());
        oldBook.setPrice(book.getPrice());
        bookRepository.save(oldBook);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
}
