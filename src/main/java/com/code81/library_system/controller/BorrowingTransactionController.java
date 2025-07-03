package com.code81.library_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.code81.library_system.entities.BorrowingTransaction;
import com.code81.library_system.service.BorrowingTransactionService;

@RestController
@RequestMapping("/api/borrowing-transactions")
public class BorrowingTransactionController {
     private final BorrowingTransactionService borrowingTransactionService;
    
    public BorrowingTransactionController(BorrowingTransactionService borrowingTransactionService) {
        this.borrowingTransactionService = borrowingTransactionService;
    }

    @GetMapping
    public ResponseEntity<List<BorrowingTransaction>> getAllBorrowingTransactions() {
        List<BorrowingTransaction> transactions = borrowingTransactionService.getAllBT();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingTransaction> getBorrowingTransactionById(@PathVariable Long id) {
        Optional<BorrowingTransaction> transaction = borrowingTransactionService.getBTById(id);
        return transaction.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BorrowingTransaction> createBorrowingTransaction(@RequestBody BorrowingTransaction transaction) {
        BorrowingTransaction createdTransaction = borrowingTransactionService.createBT(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingTransaction> updateBorrowingTransaction(@PathVariable Long id, @RequestBody BorrowingTransaction transaction) {
        transaction.setId(id);
        BorrowingTransaction updatedTransaction = borrowingTransactionService.updateBT(transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingTransaction(@PathVariable Long id) {
        borrowingTransactionService.deleteBT(id);
        return ResponseEntity.noContent().build();
    }

}
