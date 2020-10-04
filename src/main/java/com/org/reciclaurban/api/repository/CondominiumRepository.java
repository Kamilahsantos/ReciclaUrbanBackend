package com.org.reciclaurban.api.repository;

import com.org.reciclaurban.api.model.Condominium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CondominiumRepository extends JpaRepository<Condominium, Long> {
}
