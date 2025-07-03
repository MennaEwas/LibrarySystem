package com.code81.library_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.code81.library_system.entities.BorrowingTransaction;
import com.code81.library_system.entities.Member;
import com.code81.library_system.entities.Book;


@Repository
public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long>{
    
    List<BorrowingTransaction> findByMember(Member member);
    List<BorrowingTransaction> findByBook(Book book);
    List<BorrowingTransaction> findByReturnDateIsNotNull();
    List<BorrowingTransaction> findByReturnDateIsNull();
}
