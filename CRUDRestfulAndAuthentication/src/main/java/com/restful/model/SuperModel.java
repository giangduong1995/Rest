package com.restful.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperModel implements Serializable {

	private static final long serialVersionUID = 1820464383344198812L;

	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date createdDate;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	@Version
	private Integer version;

	@PrePersist
	void onCreate() {
		if (this.createdDate == null) {
			this.setCreatedDate(new Date());
		}

		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (this.createdBy == null) {
			this.setCreatedBy(principal.getUsername());
		}

	}

	@PreUpdate
	void onUpdate() {
		this.setUpdatedDate(new Date());
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.setUpdatedBy(principal.getUsername());
	}

}
