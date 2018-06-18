package com.qa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;

import java.util.List;

import com.google.gson.Gson;
import com.qa.domain.Account;

@Transactional(SUPPORTS)
public class DBImpl {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Transactional(REQUIRED)
	public String createAccount(String newAccJSON) {
		Account newAcc = new Gson().fromJson(newAccJSON, Account.class);
		em.persist(newAcc);
		return newAcc.toString();
	}
	
	@Transactional(REQUIRED)
	public void updateAccountFirstName(long accountID, String newFirstName) {
		Account account = em.find(Account.class, accountID);
		account.setFirstName(newFirstName);
	}
	
	@Transactional(REQUIRED)
	public void updateAccountSecondName(long accountID, String newSecondName) {
		Account account = em.find(Account.class, accountID);
		account.setSecondName(newSecondName);
	}
	
	@Transactional(REQUIRED)
	public void updateAccountNumber(long accountID, String newAccountNumber) {
		Account account = em.find(Account.class, accountID);
		account.setAccountNumber(newAccountNumber);
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(long accountID) {
		Account account = em.find(Account.class, accountID);
		if (account != null ) {
			em.remove(account);
			return "{\r\n" + "	\"message\": \"account removed\"\r\n" +	"}";
		}
		else {
			return "{\r\n" + "	\"message\": \"no object found in database\"\r\n" +	"}";
		}
		
	}
	
	public String getAllAccountsJson() {
		TypedQuery<Account> query = em.createQuery("SELECT a FROM ACCOUNT a ORDER BY a.firstName ASC", Account.class);
		List<Account> accounts = query.getResultList();
		if (accounts.size() != 0) {
			return new Gson().toJson(accounts);
		}
		else {
			return "{\r\n" + "	\"message\": \"no object found in database\"\r\n" +	"}";
		}
		
	}
	
	public Account getAccount(long id) {
		return em.find(Account.class, id);
	}
	
	public String getAccountJson(long id) {
		Account foundAccount = em.find(Account.class, id);
		if (foundAccount != null) {
			return new Gson().toJson(foundAccount);
		}
		else {
			return "{\r\n" + "	\"message\": \"no object found in database\"\r\n" +	"}";
		}
		
	}
	
}
