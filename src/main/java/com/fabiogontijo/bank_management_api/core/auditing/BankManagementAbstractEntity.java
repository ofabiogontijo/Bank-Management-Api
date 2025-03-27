package com.fabiogontijo.bank_management_api.core.auditing;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder
@MappedSuperclass
@EqualsAndHashCode
@NoArgsConstructor
@ToString(callSuper = true)
@EntityListeners({ AuditingEntityListener.class })
public abstract class BankManagementAbstractEntity<T> implements Serializable {

	@CreatedDate
	@Column(updatable = false, name = "created_at")
	private Instant createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private Instant updatedAt;

}
