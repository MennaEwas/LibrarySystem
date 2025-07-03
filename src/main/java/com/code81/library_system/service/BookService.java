package com.code81.library_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.code81.library_system.entities.Author;
import com.code81.library_system.entities.Book;
import com.code81.library_system.entities.BookCategory;
import com.code81.library_system.repository.BookRepository;

@Service
public class BookService {

    public final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    
    }

    public Optional<Book> getBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }

    public List<Book> getBookByCategory(BookCategory category){
        return bookRepository.findByCategory(category);
    }

    public List<Book> searchBookByTitle(String title){
        return bookRepository.findByTitle(title);
    }

    public List<Book> getAvailableBooks(){
        return bookRepository.findByAvailableCopiesGreaterThan(0);
    }

    public List<Book> getBooksByAuthor(Author author){
        return bookRepository.findByAuthorsContaining(author);
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }
    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);

    }

    //borrow book 
    public boolean borrowBook(Long bookId){
        //get query 
        Optional<Book> bookO = bookRepository.findById(bookId);
        //check if not non 
        if (bookO.isPresent()) {
            Book book = bookO.get();
            if (book.getAvailableCopies() > 1){
                book.setAvailableCopies(book.getAvailableCopies() - 1); 
                bookRepository.save(book);
                return true;
            }

        }
        return false;

    }


    //return the book

    public void returnBook(Long id){
        //get query 
        Optional<Book> bookO = bookRepository.findById(id);
        //check if not non 
        if (bookO.isPresent()) {
            Book book = bookO.get();
            if (book.getAvailableCopies() < book.getTotalCopies()){
                book.setAvailableCopies(book.getAvailableCopies() + 1); 
                bookRepository.save(book);
            }
        }
    }
    
}
