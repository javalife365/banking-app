package com.ourbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "customer_invitations",
        indexes = {
                @Index(
                        name = "idx_invitation_customer",
                        columnList = "customer_id"
                ),
                @Index(
                        name = "idx_invitation_expires_at",
                        columnList = "expires_at"
                )
        }
)
public class CustomerInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = " customer_id", nullable = false)
    private Customer customer;

    @Column(name = "token_hash", nullable = false, unique = true, length = 64)
    private String tokenHash;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name ="used_at")
    private LocalDateTime usedAt;

    protected CustomerInvitation(){}

    public CustomerInvitation(Customer customer, String tokenHash, LocalDateTime expiresAt){
        this.customer = customer;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
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

    public String getTokenHash() {
        return tokenHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public void markAsUsed(){
        this.usedAt = LocalDateTime.now();
    }
}
