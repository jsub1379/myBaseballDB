package com.example.mybaseballdb.repository;

import com.example.mybaseballdb.model.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PitcherRepository extends JpaRepository<Pitcher, String> {
}
