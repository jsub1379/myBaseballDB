package com.example.mybaseballdb.service;

import com.example.mybaseballdb.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Object[]> getTeamInfoWithTopPlayers(String teamName) {
        return teamRepository.getTeamInfoWithTopPlayers(teamName);
    }
}
