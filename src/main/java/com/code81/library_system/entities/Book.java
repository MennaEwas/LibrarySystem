package com.code81.library_system.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.code81.library_system.entities.BookCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String publicationYear;

    @Column(nullable = false)
    private String edition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookCategory category;

    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies = 1;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies = 1;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

}
