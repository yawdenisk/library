package com.example.library.Handler;

public class BookNotFoundException extends CustomException{
    @Override
    public String getMessage() {
        return "Book not found";
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Nie znalazłem książki, o które prosisz";
    }
}
