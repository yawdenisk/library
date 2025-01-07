package com.example.library.Controllers;

import com.example.library.Entities.Book;
import com.example.library.Handler.AuthorNotFoundException;
import com.example.library.Handler.BookNotFoundException;
import com.example.library.Services.AuthorService;
import com.example.library.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBook(@PathVariable UUID id) {
        Book book = bookService.getBook(id).orElseThrow(() -> new BookNotFoundException());
        return ResponseEntity.ok(book);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            book.setAuthor(authorService.getAuthor(book.getAuthor().getAuthorId()).orElseThrow(() -> new AuthorNotFoundException()));
            bookService.addBook(book);
            return ResponseEntity.ok("Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable UUID id) {
        try {
            bookService.updateBook(id, book);
            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) {
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
