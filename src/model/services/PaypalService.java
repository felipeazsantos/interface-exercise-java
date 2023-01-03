package model.services;

public class PaypalService implements OnlinePaymentService {

	private double feeValue;
	private double interestValue;
	
	public PaypalService(double feeValue, double interestValue) {
		this.feeValue = feeValue;
		this.interestValue = interestValue;
	}
	
	public Double paymentFee(Double amount) {
		return amount + (amount * feeValue);
	}
	
	public Double interest(Double amount, Integer months) {
		return amount + ((amount *  interestValue) * months);
	}
	
}
