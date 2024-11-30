package com.example.mybaseballdb.repository;

import com.example.mybaseballdb.model.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PitcherRepository extends JpaRepository<Pitcher, String> {
    @Query(value = """
        SELECT 
            p.player_name, p.team, p.games_played, p.earned_run_average, p.wins, p.losses, p.saves, p.holds
        FROM Pitcher p
        WHERE p.player_name = :playerName
        """, nativeQuery = true)
    List<Object[]> getPitcherSummary(@Param("playerName") String playerName);


    @Query(value = """
        SELECT 
            player_name, team, innings_pitched, wins, losses, saves, holds, earned_run_average, whip, k_per_bb,
            RANK() OVER (ORDER BY CASE 
            WHEN :stat = 'innings_pitched' THEN innings_pitched
            WHEN :stat = 'wins' THEN wins
            WHEN :stat = 'losses' THEN losses
            WHEN :stat = 'saves' THEN saves
            WHEN :stat = 'holds' THEN holds
            WHEN :stat = 'earned_run_average' THEN earned_run_average
            WHEN :stat = 'whip' THEN whip
            WHEN :stat = 'k_per_bb' THEN k_per_bb
            ELSE earned_run_average END ASC) AS ranking
        FROM Pitcher
        WHERE :stat IS NOT NULL
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> getAscendingRankingByStat(@Param("stat") String stat);

    @Query(value = """
        SELECT 
            player_name, team, innings_pitched, wins, losses, saves, holds, earned_run_average, whip, k_per_bb,
            RANK() OVER (ORDER BY CASE 
            WHEN :stat = 'innings_pitched' THEN innings_pitched
            WHEN :stat = 'wins' THEN wins
            WHEN :stat = 'losses' THEN losses
            WHEN :stat = 'saves' THEN saves
            WHEN :stat = 'holds' THEN holds
            WHEN :stat = 'earned_run_average' THEN earned_run_average
            WHEN :stat = 'whip' THEN whip
            WHEN :stat = 'k_per_bb' THEN k_per_bb
            ELSE earned_run_average END DESC) AS ranking
        FROM Pitcher
        WHERE :stat IS NOT NULL
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> getDescendingRankingByStat(@Param("stat") String stat);

}
