package com.example.library.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @UuidGenerator
    private UUID authorId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<Book> books = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
}
