package com.vastag.sb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vastag.sb.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}