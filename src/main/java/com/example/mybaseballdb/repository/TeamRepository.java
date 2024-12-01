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
            s.seating_capacity AS seatingCapacity,
            s.ticket_price AS ticketPrice
        FROM Team t
        JOIN Stadium s ON t.home_stadium = s.stadium_name
        WHERE t.team_name = :teamName
        """, nativeQuery = true)
    List<Object[]> getTeamAndStadiumInfo(@Param("teamName") String teamName);

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
                p.earned_run_average AS earningRunAverage,
                b.player_name AS batterName,
                b.ops AS ops
            FROM Team t
            LEFT JOIN Pitcher p ON t.team_name = p.team
            LEFT JOIN Batter b ON t.team_name = b.team
            WHERE t.team_name = :teamName
            ORDER BY p.earned_run_average ASC, b.ops DESC
            LIMIT 5
            """, nativeQuery = true)
    List<Object[]> getTeamInfoWithTopPlayers(@Param("teamName") String teamName);
    @Query(value = """
        SELECT 
            p.player_name AS playerName,
            p.earned_run_average AS stat,
            '투수' AS playerType
        FROM Team t
        JOIN Pitcher p ON t.team_name = p.team
        WHERE t.team_name = :teamName
        ORDER BY p.earned_run_average ASC
        LIMIT 5
        """, nativeQuery = true)
    List<Object[]> getTopPitchersWithJoin(@Param("teamName") String teamName);

    @Query(value = """
        SELECT 
            b.player_name AS playerName,
            b.ops AS stat,
            '타자' AS playerType
        FROM Team t
        JOIN Batter b ON t.team_name = b.team
        WHERE t.team_name = :teamName
        ORDER BY b.ops DESC
        LIMIT 5
        """, nativeQuery = true)
    List<Object[]> getTopBattersWithJoin(@Param("teamName") String teamName);


}
