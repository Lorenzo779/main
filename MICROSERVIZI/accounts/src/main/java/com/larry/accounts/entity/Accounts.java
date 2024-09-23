package com.larry.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity {
	
	// Il campo customer id serve per
	// stabilire la relazione col customer
	@Column(name ="customer_id")
	private Long customerId;
	
	@Column(name="account_number")
	@Id
	private Long accountNumber;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name ="branch_address")
	private String branchAddress;

}
