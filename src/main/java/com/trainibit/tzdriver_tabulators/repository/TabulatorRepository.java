package com.trainibit.tzdriver_tabulators.repository;

import com.trainibit.tzdriver_tabulators.entity.Tabulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabulatorRepository extends JpaRepository<Tabulator, Long> {

}
