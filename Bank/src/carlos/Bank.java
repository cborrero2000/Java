package carlos;

class Bank {

	public static void main(String as[]) {
		Account a=new SavingAccount();
		System.out.println(a.bankName+" "+a.getBankName());
	}

}

class Account {

	String bankName="ABC";

	public String getBankName() { 
		return bankName;
	}

}

class SavingAccount extends Account {

	String bankName="XYZ";

	public String getBankName(){ 
		return bankName; 
	}

}