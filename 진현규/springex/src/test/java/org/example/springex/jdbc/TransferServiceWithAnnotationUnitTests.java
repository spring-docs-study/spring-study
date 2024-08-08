package org.example.springex.jdbc;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransferServiceWithAnnotationUnitTests {
	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private TransferService transferService;

	@Test
	@DisplayName("")
	void TransferServiceWithAnnotationUnitTests() {
	    // given
		Account sender = new Account();
		sender.setAmount(new BigDecimal(1000));
		sender.setId(1L);

		BDDMockito.given(accountRepository.findById(1L))
			.willReturn(Optional.of(sender));

		BDDMockito.given(accountRepository.findById(2L))
			.willReturn(Optional.empty());

//		Assertions.assertThrows(AccountNotFoundException.class() -> transferService.transferMoney(1L,2L,new BigDecimal(100)));
	    // when

	    // then
		BDDMockito.verify(accountRepository, Mockito.never())
			.changeAmount(BDDMockito.anyLong(), BDDMockito.any());
	}
}
