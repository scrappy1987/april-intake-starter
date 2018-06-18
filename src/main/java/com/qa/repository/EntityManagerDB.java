package com.qa.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType.*;

import java.util.List;

import com.google.gson.Gson;
import com.qa.domain.Account;

@Transactional(SUPPORTS)
@Default
@ApplicationScoped
public class EntityManagerDB implements Database{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Transactional(REQUIRED)
	public String createAccount(String newAccJSON) {
		Account newAcc = new Gson().fromJson(newAccJSON, Account.class);
		em.persist(newAcc);
		return "{\r\n" + "	\"message\": \"account created\"\r\n" +	"}";
	}
	
	@Transactional(REQUIRED)
	public String updateAccountFirstName(long accountID, String newFirstName) {
		Account account = em.find(Account.class, accountID);
		if (account != null) {
			account.setFirstName(newFirstName);
			return "{\r\n" + "	\"message\": \"account updated\"\r\n" +	"}";
		}
		else {
			return "{\r\n" + "	\"message\": \"no account found\"\r\n" +	"}";
		}
		
	}
	
	@Transactional(REQUIRED)
	public String updateAccountSecondName(long accountID, String newSecondName) {
		Account account = em.find(Account.class, accountID);
		if (account != null) {
			account.setSecondName(newSecondName);
			return "{\r\n" + "	\"message\": \"account updated\"\r\n" +	"}";
		}
		else {
			return "{\r\n" + "	\"message\": \"no account found\"\r\n" +	"}";
		}
		
	}
	
	@Transactional(REQUIRED)
	public String updateAccountNumber(long accountID, String newAccountNumber) {
		Account account = em.find(Account.class, accountID);
		if (account != null) {
			account.setAccountNumber(newAccountNumber);
			return "{\r\n" + "	\"message\": \"account updated\"\r\n" +	"}";
		}
		else {
			return "{\r\n" + "	\"message\": \"no account found\"\r\n" +	"}";
		}
		
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
