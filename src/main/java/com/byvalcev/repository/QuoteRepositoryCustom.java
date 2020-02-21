package com.byvalcev.repository;

import java.util.List;

public interface QuoteRepositoryCustom {

    List<Double> selectElvls(int limit);

    void updateElvl(Double elvl, int id);
}
