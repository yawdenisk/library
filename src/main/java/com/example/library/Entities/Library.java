package com.example.library.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "library")
public class Library {
    @Id
    @UuidGenerator
    private UUID id;
    @OneToMany(mappedBy = "library")
    private List<Book> books = new ArrayList<>();
    @OneToMany(mappedBy = "library")
    private List<Author> authors = new ArrayList<>();
}
