package com.qa.service;

public interface AccountService {
	public String createAccount(String AccountJson);
	public String updateAccountFirstName(long accountID, String newFirstName);
	public String updateAccountSecondName(long accountID, String newSecondName);
	public String updateAccountNumber(long accountID, String newAccountNumber);
	public String deleteAccount(long accountID);
	public String getAllAccounts();
	public String getAccount(long accountID);
}
