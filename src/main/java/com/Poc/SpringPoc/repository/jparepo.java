package com.Poc.SpringPoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Poc.SpringPoc.Entity.studentEntity;

public interface jparepo extends JpaRepository<studentEntity, Long> {

}
