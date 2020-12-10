package com.customer.relationship.Repository;

import com.customer.relationship.model.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    Page<Transactions> findByUserId(Long userId, Pageable pageable);
    Optional<Transactions> findByIdAndUserId(Long id, Long userId);
}
