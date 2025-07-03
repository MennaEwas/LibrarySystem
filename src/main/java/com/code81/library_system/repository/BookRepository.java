package com.code81.library_system.repository;

import com.code81.library_system.entities.Book;    
import com.code81.library_system.entities.Author;       


import com.code81.library_system.entities.BookCategory; 
          
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;                                            
import java.util.Optional; 

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByCategory(BookCategory category);
    List<Book> findByTitle(String title);
    List<Book> findByAvailableCopiesGreaterThan(Integer copy);
    List<Book> findByAuthorsContaining(Author author);
}

