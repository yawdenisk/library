package com.example.library.Services;

import com.example.library.Entities.Author;
import com.example.library.Handler.AuthorNotFoundException;
import com.example.library.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Optional<Author> getAuthor(UUID id) {
        return authorRepository.findById(id);
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
    public Author updateAuthor(UUID id, Author author) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException());
        existingAuthor.setName(author.getName());
        existingAuthor.setSurname(author.getSurname());
        return authorRepository.save(existingAuthor);
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}
