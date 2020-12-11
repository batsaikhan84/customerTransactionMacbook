package com.customer.relationship.controller;

import com.customer.relationship.Repository.TransactionRepository;
import com.customer.relationship.Repository.UserRepository;
import com.customer.relationship.exception.CustomerTransactionNotFoundException;
import com.customer.relationship.exception.UserNotFoundException;
import com.customer.relationship.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{userId}/transactions")
    public Page<Transactions> getTransactionsByUser(@PathVariable (value = "userId") Long userId, Pageable pageable) {
        return transactionRepository.findByUserId(userId, pageable);
    }
    @GetMapping("/users/{userId}/transactions/{transactionId}")
    public ResponseEntity<Transactions> getTransaction(@PathVariable(value = "userId") Long userId, @PathVariable(value = "transactionId") Long transactionId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException("ID: " + userId + " not found");
        }
        return transactionRepository.findByIdAndUserId(transactionId, userId).map(transaction -> ResponseEntity.ok().body(transaction)).
                orElseThrow(() -> new CustomerTransactionNotFoundException("ID: " + transactionId + " not found"));
    }
    @PostMapping("/users/{userId}/transactions")
    public Transactions addTransaction(@PathVariable (value = "userId") Long userId, @RequestBody Transactions transactions) {
        return userRepository.findById(userId).map(user -> {
            transactions.setUser(user);
            return transactionRepository.save(transactions);
        }).orElseThrow(() -> new CustomerTransactionNotFoundException("Transaction not found"));
    }
    @PutMapping("/users/{userId}/transactions/{transactionId}")
    public Transactions updateTransaction(@PathVariable(value = "userId") Long userId, @PathVariable(value = "transactionId") Long transactionId,
                                          @RequestBody Transactions transactionDetails) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("ID: " + userId + " not found");
        }
        return transactionRepository.findById(transactionId).map(transaction -> {
            transaction.setAmount(transactionDetails.getAmount());
            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new CustomerTransactionNotFoundException("ID: " + userId + " not found"));
    }
    @DeleteMapping("/users/{userId}/transactions/{transactionId}")
    public Map<String, String> deleteTransaction(@PathVariable(value = "userId") Long userId, @PathVariable(value = "transactionId") Long transactionId,
                                                 @RequestBody Transactions transactionDetails) {
        return transactionRepository.findByIdAndUserId(transactionId, userId).map(transaction -> {
            transactionRepository.delete(transaction);
            Map<String, String> deleteTransaction = new HashMap<>();
            deleteTransaction.put("ID: " + userId, " deleted");
            return deleteTransaction;
        }).orElseThrow(() -> new CustomerTransactionNotFoundException("ID: " + transactionId + " not found"));
    }
}
