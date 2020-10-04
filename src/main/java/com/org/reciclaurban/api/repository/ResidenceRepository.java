package com.org.reciclaurban.api.repository;

import com.org.reciclaurban.api.model.Residence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenceRepository  extends JpaRepository<Residence, Long> {
}