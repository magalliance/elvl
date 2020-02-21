package com.byvalcev.repository;

import com.byvalcev.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer>, QuoteRepositoryCustom {
    List<Quote> findByIsin(String isin);
}
