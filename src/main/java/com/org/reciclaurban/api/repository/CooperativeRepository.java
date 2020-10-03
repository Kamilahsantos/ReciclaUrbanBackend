package com.org.reciclaurban.api.repository;

import com.org.reciclaurban.api.model.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {
}
