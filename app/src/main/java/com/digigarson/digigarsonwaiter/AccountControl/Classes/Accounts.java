package com.digigarson.digigarsonwaiter.AccountControl.Classes;

public class Accounts {
    public Accounts(int accountID, String accountName, String branchCode, String verifyCode){ this.account_id = accountID; this.account_name = accountName; this.branch_code = branchCode; this.verify_code = verifyCode; }

    public int account_id = 0;
    public String account_name = "";
    public String branch_code = "";
    public String verify_code = "";
}
