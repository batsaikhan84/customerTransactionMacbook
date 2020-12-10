package com.customer.relationship.Repository;

import com.customer.relationship.model.Transactions;
import com.customer.relationship.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
