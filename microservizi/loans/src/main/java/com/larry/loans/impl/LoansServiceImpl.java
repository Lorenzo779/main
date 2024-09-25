package com.larry.loans.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.larry.loans.constants.LoansConstants;
import com.larry.loans.dto.LoansDto;
import com.larry.loans.entity.Loans;
import com.larry.loans.exception.LoanAlreadyExistsException;
import com.larry.loans.exception.ResourceNotFoundException;
import com.larry.loans.mapper.LoansMapper;
import com.larry.loans.repository.LoansRepository;
import com.larry.loans.service.LoansService;

@Service
public class LoansServiceImpl implements LoansService{
	
	@Autowired
	private LoansRepository loansRepository;

	@Override
	public void createLoan(String mobileNumber) {
		// Vediamo se esiste già un prestito
		// per quel numero cellulare
		Optional<Loans> optionalLoan = loansRepository.findByMobileNumber(mobileNumber);
		if (optionalLoan.isPresent()) {
			throw new LoanAlreadyExistsException("Prestito già registrato con il cellulare "+ mobileNumber);
		};
		
		// Inseriamo il nuovo prestito
		loansRepository.save(createNewLoan(mobileNumber));
	}
	
	private Loans createNewLoan(String mobileNumber) {
		Loans newLoan = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		// cerchiamo
		Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow( 
			()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		
		// Convertiamo in dto, il prestito trovato
		LoansDto loanDto = LoansMapper.maptoLoansDto(loan, new LoansDto());
		return loanDto;
	}

	@Override
	public boolean updateLoan(LoansDto loansDto) {
		// Cerchiamo il prestito
		Loans loan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow( 
				()-> new ResourceNotFoundException("Loans", "loanNumber", loansDto.getLoanNumber()));
		
		// Aggiorniamo
		LoansMapper.maptoLoans(loansDto, loan);
		loansRepository.save(loan);
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow( 
			()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		loansRepository.deleteById(loan.getLoanId());;
		return true;
	}

}
