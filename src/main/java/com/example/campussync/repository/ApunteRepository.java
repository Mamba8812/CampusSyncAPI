package com.example.campussync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.campussync.model.Apunte;

public interface ApunteRepository extends JpaRepository<Apunte, Long> {
}