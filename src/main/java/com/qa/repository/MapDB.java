package com.qa.repository;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;

import com.google.gson.Gson;
import com.qa.domain.Account;




@Transactional(SUPPORTS)
@Alternative
@ApplicationScoped
public class MapDB implements Database{
	
	
	private Map<Long, Account> accountMap;
	
	
	public String createAccount(String newAccJSON) {
		Account newAcc = new Gson().fromJson(newAccJSON, Account.class);
		if (newAcc != null) {
			if (newAcc.getAccountNumber() != "9999") {
				accountMap.put(newAcc.getId(), newAcc);
				return "{\r\n" + "	\"message\": \"account created\"\r\n" +	"}";
			}
			else {
				return "{\r\n" + "	\"message\": \"account is blocked\"\r\n" +	"}";
			}
		}
		return "{\r\n" + "	\"message\": \"account creation invalid\"\r\n" +	"}";
	}

	public String updateAccountFirstName(long accountID, String newFirstName) {
		Account foundAccount = accountMap.get(accountID);
		if (foundAccount != null) {
			foundAccount.setFirstName(newFirstName);
			return "{\r\n" + "	\"message\": \"account first name updated\"\r\n" +	"}";
		}
		return "{\r\n" + "	\"message\": \"account not found\"\r\n" +	"}";
	}

	public String updateAccountSecondName(long accountID, String newSecondName) {
		Account foundAccount = accountMap.get(accountID);
		if (foundAccount != null) {
			foundAccount.setSecondName(newSecondName);
			return "{\r\n" + "	\"message\": \"account second name updated\"\r\n" +	"}";
		}
		return "{\r\n" + "	\"message\": \"account not found\"\r\n" +	"}";
	}

	public String updateAccountNumber(long accountID, String newAccountNumber) {
		Account foundAccount = accountMap.get(accountID);
		if (foundAccount != null) {
			foundAccount.setAccountNumber(newAccountNumber);
			return "{\r\n" + "	\"message\": \"account number updated\"\r\n" +	"}";
		}
		return "{\r\n" + "	\"message\": \"account not found\"\r\n" +	"}";
	}

	public String deleteAccount(long accountID) {
		accountMap.remove(accountID);
		return "{\r\n" + "	\"message\": \"account deleted\"\r\n" +	"}";
	}

	public String getAllAccountsJson() {
		if (accountMap.size()!= 0) {
			return new Gson().toJson(accountMap);
		}
		else {
			return "{\r\n" + "	\"message\": \"map is empty\"\r\n" +	"}";
		}
	}

	public String getAccountJson(long id) {
		Account foundAccount = accountMap.get(id);
		if(foundAccount != null) {
			return new Gson().toJson(accountMap);
		}
		else {
			return "{\r\n" + "	\"message\": \"account not found\"\r\n" +	"}";
		}
	}

}
