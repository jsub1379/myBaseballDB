package com.example.mybaseballdb.repository;

import com.example.mybaseballdb.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, String> {

    @Query(value = """
            SELECT 
                t.team_name AS teamName,
                t.home_stadium AS homeStadium,
                t.founding_year AS foundingYear,
                t.championships AS championships,
                t.team_batting_average AS teamBattingAverage,
                t.team_on_base_percentage AS teamOnBasePercentage,
                t.team_slugging_percentage AS teamSluggingPercentage,
                t.team_ops AS teamOps,
                t.team_earned_run_average AS teamEarnedRunAverage,
                t.team_whip AS teamWhip,
                t.team_k_per_bb AS teamKPerBB,
                p.player_name AS pitcherName,
                p.games_played AS pitcherGamesPlayed,
                b.player_name AS batterName,
                b.games_played AS batterGamesPlayed
            FROM Team t
            LEFT JOIN Pitcher p ON t.team_name = p.team
            LEFT JOIN Batter b ON t.team_name = b.team
            WHERE t.team_name = :teamName
            ORDER BY p.games_played DESC, b.games_played DESC
            LIMIT 5
            """, nativeQuery = true)
    List<Object[]> getTeamInfoWithTopPlayers(@Param("teamName") String teamName);
}
