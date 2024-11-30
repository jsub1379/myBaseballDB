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
            @RequestParam(defaultValue = "두산 베어스") String teamName, // 기본값 설정
            Model model) {
        List<Object[]> teamData = teamService.getTeamInfoWithTopPlayers(teamName);

        if (!teamData.isEmpty()) {
            Object[] teamInfo = teamData.get(0); // 팀 정보는 첫 번째 데이터에서 가져옴
            model.addAttribute("teamInfo", teamInfo);
        }

        model.addAttribute("players", teamData); // 선수 정보
        model.addAttribute("teamName", teamName);
        return "team";
    }
}
