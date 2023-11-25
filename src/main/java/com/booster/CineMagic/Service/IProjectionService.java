package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Projection;
import com.booster.CineMagic.Enum.ProjectionCriteria;

import java.util.List;

public interface IProjectionService {
    List<Projection> getProjections();
    List<Projection> getProjectionsByCriteria(ProjectionCriteria criteria, String value);
    Projection getProjectionById(Integer id);
    Projection checkUsageOfRoomHour(Projection checkProjection);
    Projection addNewProjection(Projection newProjection);
    Projection modifyProjectionById(Integer id, Projection modifyProjection);
    boolean deleteProjectionById(Integer id);
}
