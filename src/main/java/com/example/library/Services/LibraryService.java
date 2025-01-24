package com.example.library.Services;

import com.example.library.Entities.Book;
import com.example.library.Entities.Library;
import com.example.library.Handler.BookNotFoundException;
import com.example.library.Handler.LibraryNotFoundException;
import com.example.library.Repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
    public void add(Library library) {
        libraryRepository.save(library);
    }

    public Optional<Library> get(UUID id) {
        return libraryRepository.findById(id);
    }

    public void delete(UUID id) {

    }

    public void updateLibrary(UUID id, Library library) {
        Library oldLibrary = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException());
        oldLibrary.setName(library.getName());
        libraryRepository.save(library);
    }
}
