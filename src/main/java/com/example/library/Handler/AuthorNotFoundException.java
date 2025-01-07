package com.example.library.Handler;

public class AuthorNotFoundException extends CustomException {
    @Override
    public String getMessage() {
        return "Author not found";
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Nie znalazłem authora, o które prosisz";
    }
}
