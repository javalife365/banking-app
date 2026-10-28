package com.ourbank.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_customer_email",
                        columnNames = "email"
                )
        }
)
public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "first_name", nullable = false, length = 50)
        private String firstName;

        @Column(name = "last_name", nullable = false, length = 50)
        private String lastName;

        @Column(name = "email", unique = true, nullable = false, length = 100)
        private String email;

        @Column(name = "phone_number", length = 20)
        private String phoneNumber;

        @Embedded
        private Address address;

        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;

        private List<BankAccount> accounts = new ArrayList<>();

        protected Customer(){

        }

        public Customer(
             String firstName,
             String lastName,
             String email,
             String phoneNumber,
             Address address
        ){
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this. address = address;
        }

        @PrePersist
        protected  void onCreate(){
                createdAt = LocalDateTime.now();
        }

       public Long getId(){
                return id;
       }

       public String getFirstName(){
                return firstName;
       }

       public String getLastName(){
                return lastName;
       }

       public String getEmail(){
                return email;
       }

       public String getPhoneNumber(){
                return phoneNumber;
       }

       public LocalDateTime getCreatedAt(){
                return createdAt;
       }

       public List<BankAccount> getAccounts(){
                return accounts;
       }
}
