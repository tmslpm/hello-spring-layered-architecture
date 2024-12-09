package com.github.tmslpm.hsla.dal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, columnDefinition = "BIGINT")
  private Long id;

  @Column(name = "audit_create_at")
  @CreatedDate
  private LocalDate createdAt;

  @Column(name = "audit_update_at")
  @LastModifiedDate
  private LocalDate updatedAt;

  @Column(name = "audit_active", nullable = false)
  private boolean active = true;

}