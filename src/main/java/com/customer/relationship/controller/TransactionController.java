package com.customer.relationship.controller;

import com.customer.relationship.Repository.TransactionRepository;
import com.customer.relationship.exception.RecordNotFoundException;
import com.customer.relationship.model.Transactions;
import com.customer.relationship.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {
    @Autowired
    public TransactionRepository transactionRepository;
    @GetMapping("/transactions")
    public String getTransactions(Model model) {
        List<Transactions> transactionList = new ArrayList<>();
        model.addAttribute("transactions", transactionList);
        return "transactionList";
    }
    @PostMapping("/transactions")
    public Transactions addTransaction(@RequestBody Transactions transactions) {
        return transactionRepository.save(transactions);
    }
    @GetMapping("/transactions/{id}")
    public ResponseEntity<Transactions> getUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
        return ResponseEntity.ok().body(transactions);
    }
    @DeleteMapping("/transactions/{id}")
    public Map<String, String> deleteUser(@PathVariable(value = "id") long id) throws RecordNotFoundException {
        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
        transactionRepository.delete(transactions);
        Map<String, String> deleteUser = new HashMap<>();
        deleteUser.put("ID: ", id + " deleted");
        return deleteUser;
    }
    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transactions> updateTransaction(@PathVariable(value = "id") long id, @RequestBody Transactions transactionDetails) throws RecordNotFoundException {
        Transactions transactions = transactionRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("ID: " + id + " not found"));
        transactions.setAmount(transactionDetails.getAmount());
        final Transactions updateTransaction = transactionRepository.save(transactions);
        return ResponseEntity.ok(updateTransaction);
    }
}
