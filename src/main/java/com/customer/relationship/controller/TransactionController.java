package com.customer.relationship.controller;

import com.customer.relationship.Repository.TransactionRepository;
import com.customer.relationship.Repository.UserRepository;
import com.customer.relationship.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
//    @GetMapping("transactions")
//    public Page<Transactions> getTransactions(Pageable pageable) {
//        return transactionRepository.findAll(pageable);
//    }
    @GetMapping("users/{userId}/transactions")
    public Page<Transactions> getTransactionsByUser(@PathVariable (value = "userId") Long userId, Pageable pageable) {
        return transactionRepository.findByUserId(userId, pageable);
    }
//    @PostMapping("/transactions")
//    public Transactions addTransaction(@RequestBody Transactions transactions) {
//        return transactionRepository.save(transactions);
//    }
    @PostMapping("/transactions/{userId}/transactions")
    public Transactions addTransaction(@PathVariable (value = "userId") Long userId, @RequestBody Transactions transactions) {
        return userRepository.findById(userId).map(user -> {
            transactions.setUser();
            return transactionRepository.save(transactions);
        });
    }
//    @GetMapping("/transactions/{id}")
//    public ResponseEntity<Transactions> getUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
//        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
//        return ResponseEntity.ok().body(transactions);
//    }
//    @DeleteMapping("/transactions/{id}")
//    public Map<String, String> deleteUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
//        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
//        transactionRepository.delete(transactions);
//        Map<String, String> deleteUser = new HashMap<>();
//        deleteUser.put("ID: ", id + " deleted");
//        return deleteUser;
//    }
//    @PutMapping("/transactions/{id}")
//    public ResponseEntity<Transactions> updateTransaction(@PathVariable(value = "id") long id, @RequestBody Transactions transactionDetails) throws RecordNotFoundException {
//        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
//        transactions.setAmount(transactionDetails.getAmount());
//        final Transactions updateTransaction = transactionRepository.save(transactions);
//        return ResponseEntity.ok(updateTransaction);
//    }
}
