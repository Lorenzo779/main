package com.larry.loans.mapper;

import com.larry.loans.dto.LoansDto;
import com.larry.loans.entity.Loans;

public class LoansMapper {

	// Da Loan a LoanDto
	public static LoansDto maptoLoansDto(Loans loans, LoansDto loansDto) {
		loansDto.setLoanNumber(loans.getLoanNumber());
		loansDto.setAmountPaid(loans.getAmountPaid());
		loansDto.setLoanType(loans.getLoanType());
		loansDto.setMobileNumber(loans.getMobileNumber());
		loansDto.setOutstandingAmount(loans.getOutstandingAmount());
		loansDto.setTotalLoan(loans.getTotalLoan());
		return loansDto;
	}
	
	// Da LoanDto a Loan
	public static Loans maptoLoans(LoansDto loansDto, Loans loans) {
		loans.setLoanNumber(loansDto.getLoanNumber());
		loans.setAmountPaid(loansDto.getAmountPaid());
		loans.setLoanType(loansDto.getLoanType());
		loans.setMobileNumber(loansDto.getMobileNumber());
		loans.setOutstandingAmount(loansDto.getOutstandingAmount());
		loans.setTotalLoan(loansDto.getTotalLoan());
		return loans;
	}
	
}
