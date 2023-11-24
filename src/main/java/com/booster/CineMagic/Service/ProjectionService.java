package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Projection;
import com.booster.CineMagic.Enum.ProjectionCriteria;
import com.booster.CineMagic.Repository.IProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectionService implements IProjectionService{
    @Autowired
    IProjectionRepository projectionRepository;

    @Override
    public List<Projection> getProjections() {

        return projectionRepository.findAll();
    }

    @Override
    public List<Projection> getProjectionsByCriteria(ProjectionCriteria criteria, String value) {
        List<Projection> allProjections = projectionRepository.findAll();
        List<Projection> projectionsByCriteria = new ArrayList<>();

        for(Projection singleProjection : allProjections){
            switch(criteria){
                case TITLE:
                    if(Objects.equals(singleProjection.getMovie().getTitle(), value)){
                        projectionsByCriteria.add(singleProjection);
                    }
                    break;
                case ROOM:
                    if(Objects.equals(singleProjection.getRoom(), value)){
                        projectionsByCriteria.add(singleProjection);
                    }
                    break;
                case HOUR:
                    if(Objects.equals(singleProjection.getHour(), value)){
                        projectionsByCriteria.add(singleProjection);
                    }
                    break;
            }
        }

        return projectionsByCriteria;
    }

    @Override
    public Projection getProjectionById(Integer id) {

        return projectionRepository.findById(id).orElse(null);
    }

    @Override
    public Projection addNewProjection(Projection newProjection) {

        return projectionRepository.save(newProjection);
    }

    @Override
    public Projection modifyProjectionById(Integer id, Projection modifyProjection) {
        if(projectionRepository.existsById(id)){
            getProjectionById(id).setMovie(modifyProjection.getMovie());
            getProjectionById(id).setRoom(modifyProjection.getRoom());
            getProjectionById(id).setHour(modifyProjection.getHour());
        }

        return projectionRepository.save(getProjectionById(id));
    }

    @Override
    public boolean deleteProjectionById(Integer id) {
        if(!projectionRepository.existsById(id)){
            return false;
        }

        projectionRepository.delete(getProjectionById(id));
        return true;
    }
    
}
