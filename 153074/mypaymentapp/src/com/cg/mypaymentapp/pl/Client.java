package com.cg.mypaymentapp.pl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.service.WalletService;
import com.cg.mypaymentapp.service.WalletServiceImpl;

public class Client {
	public static void main(String[] args) {

		WalletService service;
		Map<String, Customer> data;
		{
			data = new HashMap<String, Customer>();
			service = new WalletServiceImpl(data);

		}

		// List myList;

		Scanner sc = new Scanner(System.in);
		String ans;
		int no;
		do {
			System.out.println("**** WALLET SERVICES****");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance");
			System.out.println("3. fund transfer");
			System.out.println("4. Withdraw ");
			System.out.println("5. Deposit");

			System.out.println("Please Enter your choice: ");
			no = sc.nextInt();

			switch (no) {
			case 1:
				System.out.println("Transaction date :" + LocalDateTime.now());
				System.out.println("****Enter the Account Details*****\n");

				System.out.println("Enter the First name:\n");
				String name = sc.next();

				System.out.println("Enter the Mobile no \n");
				String mobileno = sc.next();
				System.out.println("enter amount");
				BigDecimal amount = sc.nextBigDecimal();

				Customer customer = service.createAccount(name, mobileno, amount);

				System.out.println(customer);

				break;

			case 2:
				System.out.println("Transaction date :" + LocalDateTime.now());
				System.out.println("Enter the account  no: ");

				String mobileNo = sc.next();

				Customer customer1 = service.showBalance(mobileNo);
				if(customer1!=null)
				System.out.println(customer1.getWallet());
				break;

			case 3:
				System.out.println("Transaction date :" + LocalDateTime.now());
				System.out.println("Enter the your Account number:  ");
				String fAccNo = sc.next();
				System.out.println("Enter the Account number to which you want to transfer:  ");
				String toAccNo = sc.next();
				System.out.println("enter the amount to be transferred: ");
				BigDecimal fAmount = sc.nextBigDecimal();

				Customer customer11 = service.fundTransfer(fAccNo, toAccNo, fAmount);
				System.out.println("Remaining Balance after transfer is:" + customer11.getWallet());
				break;

			case 4:
				System.out.println("Transaction date :" + LocalDateTime.now());
				System.out.println("enter your mobile no");
				String wmobileNo = sc.next();

				System.out.println("enter the amount you want to withdraw");
				Scanner sc1 = new Scanner(System.in);
				BigDecimal wdAmount = sc1.nextBigDecimal();

				Customer customerw = service.withdrawAmount(wmobileNo, wdAmount);
				if(customerw!=null)
				System.out.println("Balance after Withdrawal is:" + customerw.getWallet());
				else
					System.out.println("Invalid mobile no!!!!");
				break;

			case 5:
				System.out.println("Transaction date :" + LocalDateTime.now());
				System.out.println("enter your mobile no");
				String dmobileNo = sc.next();
				System.out.println("enter the amount you want to deposit");
				BigDecimal dpAmount = sc.nextBigDecimal();

				Customer dcustomer = service.depositAmount(dmobileNo, dpAmount);
				if(dcustomer!=null)
				System.out.println("Balance after Deposit is:" + dcustomer.getWallet());
				else
					System.out.println("Invalid mobile no!!!!!");

				break;

			default:
				System.out.println("some error in switch case");
				break;
			}
			System.out.println(" Do you want to continue yes/no");
			ans = sc.next();
		} while (ans.equals("yes") || ans.equals("YES") || ans.equals("y"));

	}
}
