package com.aiqrango.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiqrango.domain.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
