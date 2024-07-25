package org.example.springex.repository;

import java.util.List;

import org.example.springex.domain.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseRepository {
	private final JdbcTemplate jdbcTemplate;

	public PurchaseRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	/** 등록*/
	public void storePurchase(Purchase purchase) {
		String sql = "insert into purchase values(null,?,?)";
		jdbcTemplate.update(sql, purchase.getProduct(), purchase.getPrice());
	}

	/** 조회*/
	public List<Purchase> purchases() {
		String sql = "select * from purchase";

		RowMapper<Purchase> purchaseRowMapper = (r,i) -> {
			Purchase rowObj = new Purchase();
			rowObj.setId(r.getInt("id"));
			rowObj.setProduct(r.getString("product"));
			rowObj.setPrice(r.getBigDecimal("price"));
			return rowObj;
		};
		return jdbcTemplate.query(sql, purchaseRowMapper);
	}

}
