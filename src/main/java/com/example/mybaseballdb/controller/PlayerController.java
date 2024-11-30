package com.example.mybaseballdb.controller;

import com.example.mybaseballdb.model.Batter;
import com.example.mybaseballdb.model.Pitcher;
import com.example.mybaseballdb.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // Main 페이지
    @GetMapping("/")
    public String mainPage() {
        return "main";
    }

    // 선수 검색 페이지
    @GetMapping("/player/search")
    public String searchPage() {
        return "search";
    }

    // 검색 결과 페이지
    @GetMapping("/player/result")
    public String getPlayerInfo(@RequestParam String name, @RequestParam(defaultValue = "false") boolean detailed, Model model) {
        var batter = playerService.findBatterByName(name);
        var pitcher = playerService.findPitcherByName(name);

        if (batter.isPresent()) {
            if (detailed) {
                model.addAttribute("player", batter.get());
            } else {
                model.addAttribute("playerSummary", playerService.getBatterSummary(name));
            }
            model.addAttribute("type", "Batter");
        } else if (pitcher.isPresent()) {
            if (detailed) {
                model.addAttribute("player", pitcher.get());
            } else {
                model.addAttribute("playerSummary", playerService.getPitcherSummary(name));
            }
            model.addAttribute("type", "Pitcher");
        } else {
            // 메시지를 추가하고 search 페이지로 리다이렉트
            model.addAttribute("message", "선수를 찾을 수 없습니다.");
            return "search"; // search.html로 리다이렉트
        }

        model.addAttribute("detailed", detailed);
        return "result";
    }


    // 추가 및 수정 페이지
    @GetMapping("/player/add-edit")
    public String addEditPage() {
        return "add_edit";
    }

    // 타자 저장
    @PostMapping("/player/save-batter")
    public String saveBatter(@ModelAttribute Batter batter) {
        playerService.saveBatter(batter); // 트리거에 의해 계산된 데이터 저장
        return "redirect:/";
    }

    // 투수 저장
    @PostMapping("/player/save-pitcher")
    public String savePitcher(@ModelAttribute Pitcher pitcher) {
        playerService.savePitcher(pitcher); // 트리거에 의해 계산된 데이터 저장
        return "redirect:/";
    }
}
