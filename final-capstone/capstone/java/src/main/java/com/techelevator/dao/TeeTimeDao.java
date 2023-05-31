package com.techelevator.dao;

import com.techelevator.model.TeeTime;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TeeTimeDao {
    TeeTime findById(int teeTimeId) throws ChangeSetPersister.NotFoundException;

    List<TeeTime> getAllTeeTimes();

    List<TeeTime> getTeeTimesByCourse(int courseId);

    List<TeeTime> getTeeTimesByPlayer(int playerId);

    void saveTeeTime(TeeTime teeTime);

    void updateTeeTime(TeeTime teeTime);

    void deleteTeeTime(int teeTimeId);
}