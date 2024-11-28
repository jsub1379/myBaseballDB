package com.example.mybaseballdb.repository;

import com.example.mybaseballdb.model.Batter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatterRepository extends JpaRepository<Batter, String> {
}
