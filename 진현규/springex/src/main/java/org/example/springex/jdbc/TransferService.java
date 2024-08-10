package org.example.springex.jdbc;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {
	private final AccountRepository accountRepository;

	public TransferService (AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional
	public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
		Account sender = accountRepository.findById(idSender)
			.orElseThrow( () -> new AccountNotFoundException("Account Sender not found"));

		Account receiver = accountRepository.findById(idReceiver)
			.orElseThrow( () -> new AccountNotFoundException("Account Receiver not found"));

		BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
		BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

		accountRepository.changeAmount(idSender,senderNewAmount);
		accountRepository.changeAmount(idReceiver,receiverNewAmount);
	}

	public Iterable<Account> getAccounts() {
		return accountRepository.findAll();
	}

	public List<Account> findAccountsByName(String name) {
		return accountRepository.findAccountsByName(name);
	}
}
