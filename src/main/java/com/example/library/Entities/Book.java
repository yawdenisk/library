package com.example.library.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Author author;
    @Column(nullable = false)
    private Float price;
    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;
}
