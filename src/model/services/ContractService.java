package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	
	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {
		
		LocalDate contractDate = contract.getDate();
		double installmentValue = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			 
			double installmentValueWithFee = paymentService.interest(installmentValue, i);
			double installmentTotalValue = paymentService.paymentFee(installmentValueWithFee);
			
			
			LocalDate dueDate = LocalDate.of(contractDate.getYear(), contractDate.getMonthValue() + i, 
					contractDate.getDayOfMonth());
			
			
			contract.addInstallment(new Installment(dueDate, installmentTotalValue));
			
		}
	}

}
