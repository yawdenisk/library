package com.example.library.Repositories;

import com.example.library.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findById(UUID id);
}
