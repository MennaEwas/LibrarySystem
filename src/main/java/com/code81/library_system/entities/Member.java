package com.code81.library_system.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "member_id", unique = true)
    private String memberId;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "membership_date", nullable = false)
    private LocalDate membershipDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "member")
    private List<BorrowingTransaction> borrowingTransactions;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (membershipDate == null) {
            membershipDate = LocalDate.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (membershipDate == null) {
            membershipDate = LocalDate.now();
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
