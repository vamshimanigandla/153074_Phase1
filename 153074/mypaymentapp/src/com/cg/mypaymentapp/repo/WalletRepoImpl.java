package com.cg.mypaymentapp.repo;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.cg.mypaymentapp.beans.Customer;

public class WalletRepoImpl implements WalletRepo {

	private Map<String, Customer> data;

	public WalletRepoImpl(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public boolean save(Customer customer) {
		//System.out.println(customer);
	if(customer==null)
		return false;
	else
	{
		data.put(customer.getMobileNo(), customer);
		return true;
	}	
		
	}

	public Customer findOne(String mobileNo) {
		
		
		
			
			if(data.containsKey(mobileNo)) 
				
			return data.get(mobileNo);
			
			return null;	
		
	}
		
	}

