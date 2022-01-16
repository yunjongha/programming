/*
 * BankAccount.h
 *
 *  Created on: 2022. 1. 9.
 *      Author: yunjo
 */

#include <std>

#ifndef BANKACCOUNT_H_
#define BANKACCOUNT_H_

class BankAccount {
private:
	std::list<Transaction*> list;
public:
	BankAccount();

	addTransaction(int ) {
		list.add(new Transaction(Date, int amount, String purpose));
	}

	virtual ~BankAccount() {

		delete[] transactions;
	}
};

class Transaction {
};


#endif /* BANKACCOUNT_H_ */
