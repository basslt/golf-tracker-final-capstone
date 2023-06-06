package com.techelevator.dao;

import com.techelevator.model.TeeTime;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

import java.util.List;

public interface TeeTimeDao {
    TeeTime findById(int teeTimeId);

    List<TeeTime> findAll();

     TeeTime createTeeTime(TeeTime teeTime);

    void update(TeeTime teeTime);

    void delete(TeeTime teeTime);

    int getLeagueIdByTeeTimeId(int teeTimeId);

    List<TeeTime> findByCourse(int courseId);

    int getLeagueIdByUsername(String username);

    int getTeeTimeIdByMatchId(int matchId);
}

