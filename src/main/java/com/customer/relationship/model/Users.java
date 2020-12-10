package com.customer.relationship.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = true, unique = true)
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private Set<Transactions> transactionSet;

    public Set<Transactions> getTransactionSet() {
        return transactionSet;
    }

    public void setTransactionSet(Set<Transactions> transactionSet) {
        this.transactionSet = transactionSet;
    }

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
