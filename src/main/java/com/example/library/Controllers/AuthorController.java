    package com.example.library.Controllers;

    import com.example.library.Entities.Author;
    import com.example.library.Entities.AuthorResponce;
    import com.example.library.Handler.AuthorNotFoundException;
    import com.example.library.Mapper.AuthorMapper;
    import com.example.library.Services.AuthorService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.UUID;

    @RestController
    @RequestMapping("/author")
    public class AuthorController {
        @Autowired
        private AuthorService authorService;
        @Autowired
        private AuthorMapper authorMapper;
        @GetMapping("/getAll")
        public ResponseEntity<List<Author>> getAll() {
            return ResponseEntity.ok(authorService.getAll());
        }
        @GetMapping("/get/{id}")
        public ResponseEntity<?> getAuthor(@PathVariable UUID id) {
            Author author = authorService.getAuthor(id).orElseThrow(() -> new AuthorNotFoundException());
            AuthorResponce authorResponce = authorMapper.AuthorToAuthorResponce(author);
            return ResponseEntity.ok(authorResponce);
        }
        @PostMapping("/add")
        public ResponseEntity<?> addAuthor(@RequestBody Author author) {
            try {
                authorService.addAuthor(author);
                return ResponseEntity.ok("Author added successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error while adding author");
            }
        }
        @PutMapping("/update/{id}")
        public ResponseEntity<?> updateAuthor(@RequestBody Author author, @PathVariable UUID id) {
            try {
                authorService.updateAuthor(id, author);
                return ResponseEntity.ok("Author updated successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Error while updating author");
            }
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteAuthor(@PathVariable UUID id) {
            try{
                authorService.deleteAuthor(id);
                return ResponseEntity.ok("Author deleted successfully");
            }catch (Exception e) {
                return ResponseEntity.badRequest().body("Error deleting author");
            }
        }
    }
