package aplicattion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Número: ");
		int number = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate contractDate = LocalDate.parse(sc.nextLine(), fmt);
		
		System.out.print("Valor do contrato: ");
		double contractValue = sc.nextDouble();
		
		System.out.print("Entre com o número de parcelas: ");
		int installmentsQuantity = sc.nextInt();
		
		Contract contract = new Contract(number, contractDate, contractValue);
		
		ContractService contractService = new ContractService(new PaypalService(0.02, 0.01));
		contractService.processContract(contract, installmentsQuantity);
		
		System.out.println("PARCELAS: ");
		
		
		for (Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
	}
}
