package com.example.mybaseballdb.service;

import com.example.mybaseballdb.model.Batter;
import com.example.mybaseballdb.model.Pitcher;
import com.example.mybaseballdb.repository.BatterRepository;
import com.example.mybaseballdb.repository.PitcherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final BatterRepository batterRepository;
    private final PitcherRepository pitcherRepository;

    public PlayerService(BatterRepository batterRepository, PitcherRepository pitcherRepository) {
        this.batterRepository = batterRepository;
        this.pitcherRepository = pitcherRepository;
    }

    public Optional<Batter> findBatterByName(String playerName) {
        return batterRepository.findById(playerName);
    }
    public Batter saveBatter(Batter batter) {
        return batterRepository.save(batter);
    }

    public Optional<Pitcher> findPitcherByName(String playerName) {
        return pitcherRepository.findById(playerName);
    }
    public Pitcher savePitcher(Pitcher pitcher) {
        return pitcherRepository.save(pitcher);
    }
    public List<Object[]> getBatterSummary(String playerName) {
        return batterRepository.getBatterSummary(playerName);
    }

    public List<Object[]> getPitcherSummary(String playerName) {
        return pitcherRepository.getPitcherSummary(playerName);
    }


}
