package com.restful.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "username", "role", "email", "status" })
public class UserModel extends SuperModel {

	private static final long serialVersionUID = 5802999036861908148L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private long id;
	@Column(unique = true)
	private String username;
	@JsonIgnore
	private String password;
	private String email;
	private String role;
	private String status;

	@Override
	void onCreate() {

	}
}