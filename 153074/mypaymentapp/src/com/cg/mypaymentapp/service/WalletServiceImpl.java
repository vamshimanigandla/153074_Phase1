
package com.cg.mypaymentapp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.WalletRepo;
import com.cg.mypaymentapp.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {

	private WalletRepo repo;

	public WalletServiceImpl(Map<String, Customer> data) {
		repo = new WalletRepoImpl(data);
	}

	public WalletServiceImpl(WalletRepo repo) {
		super();
		this.repo = repo;
	}

	public WalletServiceImpl() {
	}

	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) throws InvalidInputException {
		// TODO Auto-generated method stub

		Scanner console = new Scanner(System.in);
		Customer customer = new Customer(name, mobileno, new Wallet(amount));
		
		
				if (validation(mobileno)) {
					boolean result = repo.save(customer);
					if (result == true)
						return customer;
					else
						return null;
				}

				else {
					
					System.out.println("Enter a valid mobile number");
					//customer.setMobileNo(console.next());
					String newno = console.next();
					createAccount(name, newno,amount);
				

			
		}
				return customer;

		
	}

	private boolean validation(String mobileno) {
		String pattern = "[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
		if (mobileno.matches(pattern)) {
			return true;
		} else
			return false;

	}

	@Override
	public Customer showBalance(String mobileno)  {
		// TODO Auto-generated method stub

		Customer customer1 = repo.findOne(mobileno);
	
			if (customer1 != null)
				return customer1;
			else
				return null;
	
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws InvalidInputException {
		// TODO Auto-generated method stub

		try {
			int flag1 = 0;
			int flag2 = 0;

			if (repo.findOne(sourceMobileNo) != null)
				flag1 = 1;
			if (repo.findOne(targetMobileNo) != null)
				flag2 = 1;

			Customer customer11 = repo.findOne(sourceMobileNo);
			Customer customer2 = repo.findOne(targetMobileNo);
			if (flag1 == 1 && flag2 == 1) {

				depositAmount(targetMobileNo, amount);
				withdrawAmount(sourceMobileNo, amount);

			} else if (flag1 == 0) {
				System.out.println("sender mobile no is invalid !!!!");
			} else if (flag2 == 0) {
				System.out.println("benefiaciary mobile no is invalid!!!!!");
			}

			return customer11;
		} catch (Exception e) {
			System.out.println("invalid input details;");
		}
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException {
		// TODO Auto-generated method stub
		try {

			Customer dcustomer = repo.findOne(mobileNo);
			if (dcustomer != null) {
				Wallet iAmount = dcustomer.getWallet();
				BigDecimal updatedBalance = iAmount.getBalance().add(amount);
				Wallet wallet = new Wallet(updatedBalance);
				dcustomer.setWallet(wallet);
				return dcustomer;
			}

		} catch (Exception e) {
			throw new InvalidInputException("Invalid mobile no");
		}
		return null;

	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		try {
			Customer wcustomer = repo.findOne(mobileNo);
			if (wcustomer != null) {
				Wallet iAmount = wcustomer.getWallet();
				BigDecimal updatedBalance = iAmount.getBalance().subtract(amount);
				Wallet wallet = new Wallet(updatedBalance);
				wcustomer.setWallet(wallet);
				return wcustomer;
			}
		} catch (Exception e) {
			System.err.println("Invalid mobile number");
			// throw new InvalidInputException("Invalid mobile no");
		}
		return null;

	}

}
