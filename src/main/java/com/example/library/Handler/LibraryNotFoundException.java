package com.example.library.Handler;

public class LibraryNotFoundException extends CustomException{
    @Override
    public String getMessage() {
        return "Library not found";
    }

    @Override
    public int getCode() {
        return 400;
    }

    @Override
    public String getDescription() {
        return "Nie znalazłem biblioteki, o które prosisz";
    }
}
