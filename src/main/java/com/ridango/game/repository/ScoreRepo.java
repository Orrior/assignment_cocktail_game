package com.ridango.game.repository;

import com.ridango.game.model.db.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findTop10ByOrderByScoreDesc();
}
