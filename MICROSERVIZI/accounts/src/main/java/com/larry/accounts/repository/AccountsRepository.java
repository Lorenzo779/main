package com.larry.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.larry.accounts.entity.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	
	// Cerchiamo l'eventuale conto relativo
	// al cliente di cui abbiamo id
	Optional<Accounts> findByCustomerId(Long customerId);
	
	// Eliminiamo un conto in base all'id cliente
	// Poichè questo metodo è stato realizzato da un utente
	// va comunicato al framework che questo metodo
	// modificherà i dati in maniera permanente e
	// gli andrebbe assegnata la transazionalità
	// in modo tale che, se l'operazione non andasse a buon fine,
	// si annullano le modifiche e si ritorna
	// allo stato iniziale
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);

}
