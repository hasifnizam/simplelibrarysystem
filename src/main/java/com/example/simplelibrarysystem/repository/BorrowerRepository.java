package com.example.simplelibrarysystem.repository;

import com.example.simplelibrarysystem.entity.BorrowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowerRepository extends JpaRepository<BorrowerEntity, String> {

    List<BorrowerEntity> findByEmail(String email);
}
