package com.example.mybaseballdb.controller;

import com.example.mybaseballdb.model.Batter;
import com.example.mybaseballdb.model.Pitcher;
import com.example.mybaseballdb.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/search")
    public String searchPage() {
        return "search"; // 검색 페이지
    }

    @GetMapping("/player/result")
    public String getPlayerInfo(@RequestParam String name, Model model) {
        Optional<Batter> batter = playerService.findBatterByName(name);
        Optional<Pitcher> pitcher = playerService.findPitcherByName(name);

        if (batter.isPresent()) {
            model.addAttribute("player", batter.get());
            model.addAttribute("type", "Batter");
        } else if (pitcher.isPresent()) {
            model.addAttribute("player", pitcher.get());
            model.addAttribute("type", "Pitcher");
        } else {
            model.addAttribute("message", "Player not found");
        }

        return "result"; // 결과 페이지
    }
}
