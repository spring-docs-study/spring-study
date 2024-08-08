package org.example.springex.jdbc;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TransferServiceIntegrationTest {

	// 스프링 컨텍스트 일부이기도 한 모의 객체를 생성한다.
	@MockBean
	private AccountRepository accountRepository;

	// 동작을 테스트할 스프링 컨텍스트에서 실제 객체를 삽인한다.
	@Autowired
	private TransferService transferService;

	@Test
	void transferServiceTransferAmountTest() {
		Account sender = new Account();
		sender.setAmount(new BigDecimal(1000));
		sender.setId(1L);

		Account receiver = new Account();
		sender.setAmount(new BigDecimal(1000));
		sender.setId(2L);

		BDDMockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(sender));
		BDDMockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(receiver));

		transferService.transferMoney(1L,2L, new BigDecimal(100));

		BDDMockito.verify(accountRepository).changeAmount(1L, new BigDecimal(900));
		BDDMockito.verify(accountRepository).changeAmount(2L, new BigDecimal(1100));
	}
}
