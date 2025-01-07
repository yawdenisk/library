    package com.example.library.Handler;

    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    public class GlobalHandler {
        @ExceptionHandler(AuthorNotFoundException.class)
        public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
        @ExceptionHandler(BookNotFoundException.class)
        public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }
