package com.code81.library_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.code81.library_system.entities.Book;
import com.code81.library_system.entities.BorrowingTransaction;
import com.code81.library_system.entities.Member;
import com.code81.library_system.repository.BorrowingTransactionRepository;
@Service

public class BorrowingTransactionService {

    private final BorrowingTransactionRepository borrowingTransactionRepository;

    public BorrowingTransactionService(BorrowingTransactionRepository borrowingTransactionRepository){
        this.borrowingTransactionRepository = borrowingTransactionRepository;
    }

    // CRUD 

    public List<BorrowingTransaction> getAllBT(){
        return borrowingTransactionRepository.findAll();
    }
    public Optional<BorrowingTransaction> getBTById(Long id){
        return borrowingTransactionRepository.findById(id);

    }

    public BorrowingTransaction createBT(BorrowingTransaction borrowingTransaction){
        return borrowingTransactionRepository.save(borrowingTransaction);
    }

    public BorrowingTransaction updateBT(BorrowingTransaction borrowingTransaction){
        return borrowingTransactionRepository.save(borrowingTransaction);
    }

    public void deleteBT(Long id){
        borrowingTransactionRepository.deleteById(id);
    }

    public List<BorrowingTransaction> findByMember(Member member){
        return borrowingTransactionRepository.findByMember(member);
    }
    public List<BorrowingTransaction> findByBook(Book book){
        return borrowingTransactionRepository.findByBook(book);
    }
    public List<BorrowingTransaction> findByReturnDateIsNotNull(){
        return borrowingTransactionRepository.findByReturnDateIsNotNull();
    }
    public List<BorrowingTransaction> findByReturnDateIsNull(){
        return borrowingTransactionRepository.findByReturnDateIsNull();
    }

}
