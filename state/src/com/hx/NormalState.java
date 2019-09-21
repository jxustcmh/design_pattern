package com.hx;

/**
 * 充当具体状态类  ====普通状态
 * @author jxlgcmh
 * @create 2019-06-14 09:23
 */
public class NormalState extends AccountState {
	
	public NormalState(Account account) {
		this.account =account;
	}
	
	public NormalState(AccountState accountState) {
		this.account=accountState.account;
		
	}
	
	@Override
	public void deposit(double amount) {
		account.setBalance(account.getBalance()+amount);
		stateCheck();
	}
	
	@Override
	public void withdraw(double amount) {
		account.setBalance(account.getBalance()-amount);
		stateCheck();
	}
	
	@Override
	public void computeInterest() {
		System.out.println("正常状态，无需支付利息！");
	}
	
	@Override
	public void stateCheck() {
		if (account.getBalance() > -2000 && account.getBalance() <= 0) {
			account.setAccountState(new OverdraftState(this));
		} else if (account.getBalance() == -2000) {
			account.setAccountState(new RestrictedState(this));
		} else if (account.getBalance() < -2000) {
			System.out.println("操作受限");
		}
	}
}
