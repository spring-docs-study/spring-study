package org.example.springex.jdbc;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

	@Query("select * from account where name = :name")
	List<Account> findAccountsByName(String name);

	@Modifying // 데이터를 변경하는 연산을 정의하는 메소드에 선언한다 -> C U D
	@Query("update account set amount = :amount where id= :id")
	void changeAmount(Long id, BigDecimal amount);

	// 지금은 인터페이스인데 어떻게 실제 동작이 일어날까?
	// 스프링 데이터는 동적 구현체를 생성하고 SpringContext 에 Bean 으로 추가한다.
}
