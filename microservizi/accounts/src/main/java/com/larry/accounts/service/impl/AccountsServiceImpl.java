package com.larry.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.larry.accounts.constants.AccountsConstants;
import com.larry.accounts.dto.AccountsDto;
import com.larry.accounts.dto.CustomerDto;
import com.larry.accounts.entity.Accounts;
import com.larry.accounts.entity.Customer;
import com.larry.accounts.exception.CustomerAlreadyExistsException;
import com.larry.accounts.exception.ResourceNotFoundException;
import com.larry.accounts.mapper.AccountsMapper;
import com.larry.accounts.mapper.CustomerMapper;
import com.larry.accounts.repository.AccountsRepository;
import com.larry.accounts.repository.CustomerRepository;
import com.larry.accounts.service.IAccountsService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
	
	
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {
		// Realizziamo l'oggetto customer passando i dati
		// del customer Dto
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		// Verifichiamo che non esista già un cliente
		// con l'attuale numero di telefono
		Optional<Customer> optionalCustomer = customerRepository
				.findByMobileNumber(customerDto.getMobileNumber());
		// Se esiste già, segnaliamo
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Esiste già un cliente registrato con il seguente cellulare" 
					+ customerDto.getMobileNumber());
		}
		// Salviamo il nuovo customer con le funzioni repository
		Customer savedCustomer = customerRepository.save(customer);
		// Realizziamo e, contemporaneamente, salviamo il soggetto
		// con un nuovo conto
		accountsRepository.save(createNewAccount(savedCustomer));
	}
	
	
	/**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }


	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		// Cerchiamo di trovare il cliente per numero telefonico
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
			()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
		);
		// Se esiste il cliente, cerchiamo il relativo conto intestato
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
			()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
		);
		// Se abbiamo superato entrambi i test di esistenza dei record, restuiamo
		// il soggetto trovato con il rispettivo conto
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));
		return customerDto;
	}


	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		// A priori il check aggiornamento è false 
		boolean isUpdated = false;
		// prendiamo il conto dal cliente in ingresso
        AccountsDto accountsDto = customerDto.getAccountsDto();
        // Se esiste il conto
        if(accountsDto !=null ){
        	// Cerchiamo il record entity
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            // Trasmettiamo al record entity, gli aggiornamenti
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            // Salviamo gli aggiornamenti
            accounts = accountsRepository.save(accounts);

            // Ricaviamo il cliente dal conto
            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
	}


	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
	}
}
