package com.converter.currency.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.converter.currency.demo.model.CurrencyRecord;
@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyRecord, Long> {
	@Query(value="SELECT * from currency cur where cur.username=:username ORDER BY cur.id DESC Limit 0,10",nativeQuery=true)
	List<CurrencyRecord> findAllByUsernameTop2ByOrderByIdDesc(@Param("username") String username);
	
	@Query(value="SELECT * from currency cur where cur.username=:username ORDER BY cur.id DESC Limit 0,1",nativeQuery=true)
	CurrencyRecord findLatest(@Param("username") String username);
}