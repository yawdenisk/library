package com.example.library.Controllers;

import com.example.library.Entities.Book;
import com.example.library.Entities.Library;
import com.example.library.Handler.LibraryNotFoundException;
import com.example.library.Services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/library")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
    @PostMapping("/add")
    public ResponseEntity<?> addLibrary(@RequestBody Library library) {
        try {
            libraryService.add(library);
            return ResponseEntity.ok().body("Library added successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding library");
        }
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getLibraryById(@PathVariable UUID id) {
        Library library = libraryService.get(id).orElseThrow(() -> new LibraryNotFoundException());
        return ResponseEntity.ok().body(library);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLibrary(@PathVariable UUID id) {
        try {
            libraryService.delete(id);
            return ResponseEntity.ok().body("Library deleted successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting library");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Library library, @PathVariable UUID id) {
        try {
            libraryService.updateLibrary(id, library);
            return ResponseEntity.ok("Library updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating library");
        }
    }
}
