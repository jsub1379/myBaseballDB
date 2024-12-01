package com.example.mybaseballdb.controller;

import com.example.mybaseballdb.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/team")
    public String getTeamInfo(
            @RequestParam(defaultValue = "두산 베어스") String teamName,
            Model model) {
        // 팀과 선수 정보 가져오기
        List<Object[]> teamData = teamService.getTeamInfoWithTopPlayers(teamName);
        if (!teamData.isEmpty()) {
            Object[] teamInfo = teamData.get(0); // 팀 정보는 첫 번째 데이터에서 가져옴
            model.addAttribute("teamInfo", teamInfo);
        }
        model.addAttribute("players", teamData); // 선수 정보

        // 야구장 정보 가져오기
        List<Object[]> stadiumData = teamService.getTeamAndStadiumInfo(teamName);
        if (!stadiumData.isEmpty()) {
            Object[] stadiumInfo = stadiumData.get(0); // 야구장 정보
            model.addAttribute("stadiumInfo", stadiumInfo);
        }
        // 상위 5명의 투수
        List<Object[]> topPitchers = teamService.getTopPitchersWithJoin(teamName);
        model.addAttribute("topPitchers", topPitchers);

        // 상위 5명의 타자
        List<Object[]> topBatters = teamService.getTopBattersWithJoin(teamName);
        model.addAttribute("topBatters", topBatters);

        model.addAttribute("teamName", teamName);
        return "team";
    }
}
