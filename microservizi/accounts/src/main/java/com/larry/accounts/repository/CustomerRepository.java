package com.larry.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.larry.accounts.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	// Funzione di ricerca cellulare per
	// evitare di inserire 2 volte lo stesso cliente
	Optional<Customer> findByMobileNumber(String mobileNumber);

}
