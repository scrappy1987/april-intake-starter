package com.qa.repository;

public interface Database {
	public String createAccount(String newAccJSON);
	public String updateAccountFirstName(long accountID, String newFirstName);
	public String updateAccountSecondName(long accountID, String newSecondName);
	public String updateAccountNumber(long accountID, String newAccountNumber);
	public String deleteAccount(long accountID);
	public String getAllAccountsJson();
	public String getAccountJson(long id);
}
