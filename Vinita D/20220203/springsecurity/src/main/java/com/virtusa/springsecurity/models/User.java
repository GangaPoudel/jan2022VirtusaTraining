package com.virtusa.springsecurity.models;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_Id")
	private long userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
//	@Transient
//    private String passwordConfirmation;
//    
//	
//	 @ManyToMany(fetch = FetchType.EAGER)
//	    @JoinTable(
//	        name = "users_roles", 
//	        joinColumns = @JoinColumn(name = "user_id"), 
//	        inverseJoinColumns = @JoinColumn(name = "role_id"))
//	    private List<Role> roles;
}
