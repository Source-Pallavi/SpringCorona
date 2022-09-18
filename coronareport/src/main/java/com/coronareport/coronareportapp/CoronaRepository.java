package com.coronareport.coronareportapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CoronaRepository extends JpaRepository<CoronaPojo,Long> {
    List<CoronaPojo> findByLastUpdateBetween(LocalDateTime from,LocalDateTime to);

    List<CoronaPojo> findByCombinedKey(String combinedKey);
}
