package com.demo.rbac.ecommerce.backend.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public class Audit {

    protected LocalDateTime created;
    protected LocalDateTime updated;

    @PrePersist
    public void initTimestamp() {
        this.created = LocalDateTime.now();
        updateUpdated();
    }

    @PreUpdate
    public void updateUpdated() {
        this.updated = LocalDateTime.now();
    }
}
