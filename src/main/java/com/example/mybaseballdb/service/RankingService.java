package com.example.mybaseballdb.service;

import com.example.mybaseballdb.repository.BatterRepository;
import com.example.mybaseballdb.repository.PitcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    private final BatterRepository batterRepository;
    private final PitcherRepository pitcherRepository;

    @Autowired
    public RankingService(BatterRepository batterRepository, PitcherRepository pitcherRepository) {
        this.batterRepository = batterRepository;
        this.pitcherRepository = pitcherRepository;
    }

    public List<Object[]> getBatterRanking(String stat, boolean ascending) {
        if (ascending) {
            return batterRepository.getAscendingRankingByStat(stat);
        } else {
            return batterRepository.getDescendingRankingByStat(stat);
        }
    }

    public List<Object[]> getPitcherRanking(String stat, boolean ascending) {
        if (ascending) {
            return pitcherRepository.getAscendingRankingByStat(stat);
        } else {
            return pitcherRepository.getDescendingRankingByStat(stat);
        }
    }

}
