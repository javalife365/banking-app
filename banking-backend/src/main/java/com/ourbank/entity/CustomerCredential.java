package com.ourbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "customer_credentials",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_customer_username", columnNames = "username"),
                @UniqueConstraint(name ="uk_customer_credential_customer", columnNames = "customer_id")
        }
)
public class CustomerCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @Column(nullable = false)
    private boolean enabled;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected CustomerCredential(){}

    public CustomerCredential(Customer customer, String username, String passwordHash){
        this.customer = customer;
        this.username = username;
        this.passwordHash = passwordHash;
        this.enabled = true;
    }

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
