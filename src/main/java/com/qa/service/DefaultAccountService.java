package com.qa.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.qa.domain.Account;
import com.qa.repository.Database;

public class DefaultAccountService implements AccountService {
	
	@Inject
	private Database accountRepository;

	private int count = 0;

	@Override
	public String createAccount(String accountJson) {
		Account newAcc = new Gson().fromJson(accountJson, Account.class);
		
		if (newAcc != null && newAcc.getAccountNumber() != "9999") {
			return accountRepository.createAccount(accountJson);
		}
		else if (newAcc.getAccountNumber() == "9999") {
			return "{\r\n" + "	\"message\": \"This account is blocked\"\r\n" +	"}";
		}
		else {
			return "{\r\n" + "	\"message\": \"Account creation invalid\"\r\n" +	"}";
		}
		
	}

	@Override
	public String updateAccountFirstName(long accountID, String newFirstName) {
		return accountRepository.updateAccountFirstName(accountID, newFirstName);
	}

	@Override
	public String updateAccountSecondName(long accountID, String newSecondName) {
		return accountRepository.updateAccountSecondName(accountID, newSecondName);
	}

	@Override
	public String updateAccountNumber(long accountID, String newAccountNumber) {
		return accountRepository.updateAccountNumber(accountID, newAccountNumber);
	}

	@Override
	public String deleteAccount(long accountID) {
		return accountRepository.deleteAccount(accountID);
	}

	@Override
	public String getAllAccounts() {
		return accountRepository.getAllAccountsJson();
	}

	@Override
	public String getAccount(long accountID) {
		return accountRepository.getAccountJson(accountID);
	}

	

}
