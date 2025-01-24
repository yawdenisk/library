package com.example.library.Controllers;

import com.example.library.Entities.Book;
import com.example.library.Entities.BookResponce;
import com.example.library.Handler.AuthorNotFoundException;
import com.example.library.Handler.BookNotFoundException;
import com.example.library.Handler.LibraryNotFoundException;
import com.example.library.Mapper.AuthorMapper;
import com.example.library.Mapper.BookMapper;
import com.example.library.Services.AuthorService;
import com.example.library.Services.BookService;
import com.example.library.Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private AuthorMapper authorMapper;
    @Cacheable("books")
    @GetMapping("/getAll")
    public ResponseEntity<List<BookResponce>> getAll() {
        List<Book> books = bookService.getAll();
        List<BookResponce> bookResponces = new ArrayList<>();
        for (Book book : books) {
            BookResponce bookResponce = bookMapper.BookToBookResponce(book);
            bookResponce.setAuthorResponce(authorMapper.AuthorToAuthorResponce(book.getAuthor()));
           bookResponces.add(bookResponce);
        }
        return ResponseEntity.ok(bookResponces);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBook(@PathVariable UUID id) {
        Book book = bookService.getBook(id).orElseThrow(() -> new BookNotFoundException());
        BookResponce bookResponce = bookMapper.BookToBookResponce(book);
        return ResponseEntity.ok(bookResponce);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            book.setLibrary(libraryService.get(book.getLibrary().getId()).orElseThrow(() -> new LibraryNotFoundException()));
            book.setAuthor(authorService.getAuthor(book.getAuthor().getId()).orElseThrow(() -> new AuthorNotFoundException()));
            bookService.addBook(book);
            return ResponseEntity.ok("Book added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while adding a book");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable UUID id) {
        try {
            bookService.updateBook(id, book);
            return ResponseEntity.ok("Book updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while updating book");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable UUID id) {
        try{
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting book");
        }
    }
}
