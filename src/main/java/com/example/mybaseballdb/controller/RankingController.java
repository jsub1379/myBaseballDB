package com.example.mybaseballdb.controller;

import com.example.mybaseballdb.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RankingController {

    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/ranking")
    public String getRanking(
            @RequestParam(defaultValue = "hits") String stat, // 기본값: hits
            @RequestParam(defaultValue = "batter") String type, // 기본값: batter
            @RequestParam(defaultValue = "asc") String order, // 기본값: asc
            Model model) {
        boolean ascending = order.equals("asc");
        model.addAttribute("stat", stat);
        model.addAttribute("type", type);

        if (type.equals("batter")) {
            model.addAttribute("ranking", rankingService.getBatterRanking(stat, ascending));
        } else if (type.equals("pitcher")) {
            model.addAttribute("ranking", rankingService.getPitcherRanking(stat, ascending));
        }

        return "ranking";
    }





}
