package com.customer.relationship.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction_table")
public class Transactions extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    public Transactions() {
    }

    public Double getAmount() {
        return amount;
    }

    public Users getUser() {
        return users;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
