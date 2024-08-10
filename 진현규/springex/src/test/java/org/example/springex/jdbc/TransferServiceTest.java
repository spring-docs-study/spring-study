package org.example.springex.jdbc;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class) // @Mock 과 @InjectMocks 어노테이션을 사용하도록 활성화 한다.
@SpringBootTest
class TransferServiceTest {

	@Mock // @Mock 어노테이션으로 모의 객체를 생성하고 테스트 클래스의 어노테이션된 필드에 주입한다.
	private AccountRepository accountRepository;

	@InjectMocks // @InjectMocks 로 테스트할 객체를 생성하고 클래스의 어노테이션된 필드에 주입한다.
	private TransferService transferService;

	@BeforeEach
	void setUp() {
		accountRepository = mock(AccountRepository.class);
	}

	@Test
	@DisplayName("")
	void moneyTransferHappyFlow() {
	    // given
		TransferService transferService = new TransferService(accountRepository);

		Account sender = new Account();
		sender.setId(1L);
		sender.setAmount(new BigDecimal("1000"));

		Account receiver = new Account();
		receiver.setId(2L);
		receiver.setAmount(new BigDecimal("1000"));

		// when
		BDDMockito.given(accountRepository.findById(sender.getId()))
			.willReturn(Optional.of(sender));

		BDDMockito.given(accountRepository.findById(receiver.getId()))
			.willReturn(Optional.of(receiver));

		transferService.transferMoney(sender.getId(), receiver.getId(), new BigDecimal("100"));

	    // then
		verify(accountRepository).changeAmount(1L, new BigDecimal(900));
		verify(accountRepository).changeAmount(2L, new BigDecimal(1100));
	}

	@Test
	@DisplayName("")
	void TransferServiceTest() {
	    // given

	    // when

	    // then
	}

}