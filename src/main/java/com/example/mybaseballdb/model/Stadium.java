package com.example.mybaseballdb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Stadium {
    @Id
    private String stadiumName; // 야구장 이름 (Primary Key)
    private int seatingCapacity; // 좌석 수
    private double ticketPrice;  // 표 가격
}
