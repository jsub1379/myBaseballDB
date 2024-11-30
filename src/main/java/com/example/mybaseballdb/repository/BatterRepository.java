package com.example.mybaseballdb.repository;

import com.example.mybaseballdb.model.Batter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatterRepository extends JpaRepository<Batter, String> {
    @Query(value = """
        SELECT 
            b.player_name, b.team, b.games_played, b.hits, b.home_runs, b.batting_average, b.ops
        FROM Batter b
        WHERE b.player_name = :playerName
        """, nativeQuery = true)
    List<Object[]> getBatterSummary(@Param("playerName") String playerName);


    @Query(value = """
        SELECT 
            player_name, team, hits, home_runs, runs_batted_in, stolen_bases, batting_average, on_base_percentage, slugging_percentage, ops,
            RANK() OVER (ORDER BY CASE 
            WHEN :stat = 'hits' THEN hits
            WHEN :stat = 'home_runs' THEN home_runs
            WHEN :stat = 'runs_batted_in' THEN runs_batted_in
            WHEN :stat = 'stolen_bases' THEN stolen_bases
            WHEN :stat = 'batting_average' THEN batting_average
            WHEN :stat = 'on_base_percentage' THEN on_base_percentage
            WHEN :stat = 'slugging_percentage' THEN slugging_percentage
            WHEN :stat = 'ops' THEN ops
            ELSE hits END ASC) AS ranking
        FROM Batter
        WHERE :stat IS NOT NULL
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> getAscendingRankingByStat(@Param("stat") String stat);

    @Query(value = """
        SELECT 
            player_name, team, hits, home_runs, runs_batted_in, stolen_bases, batting_average, on_base_percentage, slugging_percentage, ops,
            RANK() OVER (ORDER BY CASE 
            WHEN :stat = 'hits' THEN hits
            WHEN :stat = 'home_runs' THEN home_runs
            WHEN :stat = 'runs_batted_in' THEN runs_batted_in
            WHEN :stat = 'stolen_bases' THEN stolen_bases
            WHEN :stat = 'batting_average' THEN batting_average
            WHEN :stat = 'on_base_percentage' THEN on_base_percentage
            WHEN :stat = 'slugging_percentage' THEN slugging_percentage
            WHEN :stat = 'ops' THEN ops
            ELSE hits END DESC) AS ranking
        FROM Batter
        WHERE :stat IS NOT NULL
        LIMIT 10
        """, nativeQuery = true)
    List<Object[]> getDescendingRankingByStat(@Param("stat") String stat);

}
