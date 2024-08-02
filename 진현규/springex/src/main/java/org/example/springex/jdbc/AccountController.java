package org.example.springex.jdbc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	private final TransferService transferService;

	public AccountController (TransferService transferService) {
		this.transferService = transferService;
	}

	@PostMapping("/transfer")
	public void transfer(@RequestBody TransferRequest transfer) {
		transferService.transferMoney(transfer.getSenderAccountId(), transfer.getReceiverAccountId(), transfer.getAmount());
	}

	@GetMapping("/accounts")
	public Iterable<Account> getAccounts(@RequestParam(required = false) String name) {
		if(name == null) {
			return transferService.getAccounts();
		} else {
			return transferService.findAccountsByName(name);
		}
	}

}
