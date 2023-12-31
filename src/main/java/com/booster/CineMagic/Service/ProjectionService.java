package com.booster.CineMagic.Service;

import com.booster.CineMagic.Entity.Movie;
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
    @Autowired
    IMovieService movieService;

    @Override
    public List<Projection> getProjections() {

        return projectionRepository.findAll();
    }

    @Override
    public List<Projection> getProjectionsByCriteria(ProjectionCriteria criteria, String value) {
        List<Projection> allProjections = projectionRepository.findAll();
        List<Projection> projectionsByCriteria = new ArrayList<>();

        for(Projection singleProjection : allProjections) {
            switch (criteria) {
                case TITLE:
                    if (Objects.equals(singleProjection.getMovie().getTitle(), value)) {
                        projectionsByCriteria.add(singleProjection);
                    }
                    break;
                case ROOM:
                    if (Objects.equals(singleProjection.getRoom(), value)) {
                        projectionsByCriteria.add(singleProjection);
                    }
                    break;
                case HOUR:
                    if (Objects.equals(singleProjection.getHour(), value)) {
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
    public Projection checkUsageOfRoomHour(Projection checkProjection){
        List<Projection> allProjections = projectionRepository.findAll();

        for(Projection singleProjection : allProjections){
            if(Objects.equals(singleProjection.getHour(), checkProjection.getHour()) &&
                    Objects.equals(singleProjection.getRoom(), checkProjection.getRoom())){
                return singleProjection;
            }
        }

        return null;
    }

    @Override
    public boolean checkMovieIdentity(Projection checkProjection) {
        if(movieService.getMovieByID(checkProjection.getMovie().getMovieId()) == null){
            return false;
        }

        Movie movieControl = movieService.getMovieByID(checkProjection.getMovie().getMovieId());

        return Objects.equals(movieControl.getMovieId(), checkProjection.getMovie().getMovieId()) &&
                Objects.equals(movieControl.getTitle(), checkProjection.getMovie().getTitle()) &&
                Objects.equals(movieControl.getDuration(), checkProjection.getMovie().getDuration()) &&
                Objects.equals(movieControl.getCountry(), checkProjection.getMovie().getCountry()) &&
                Objects.equals(movieControl.getCategory(), checkProjection.getMovie().getCategory()) &&
                Objects.equals(movieControl.getClassification(), checkProjection.getMovie().getClassification()) &&
                Objects.equals(movieControl.getRating(), checkProjection.getMovie().getRating()) &&
                Objects.equals(movieControl.getSynopsis(), checkProjection.getMovie().getSynopsis()) &&
                Objects.equals(movieControl.getLanguage(), checkProjection.getMovie().getLanguage()) &&
                Objects.equals(movieControl.getYear(), checkProjection.getMovie().getYear()) &&
                Objects.equals(movieControl.getDirector(), checkProjection.getMovie().getDirector()) &&
                Objects.equals(movieControl.getFormat(), checkProjection.getMovie().getFormat()) &&
                Objects.equals(movieControl.getType(), checkProjection.getMovie().getType()) &&
                Objects.equals(movieControl.getWatched(), checkProjection.getMovie().getWatched()) &&
                Objects.equals(movieControl.isAvailable(), checkProjection.getMovie().isAvailable());
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

        Movie movieReference = getProjectionById(id).getMovie();
        List<Projection> projectionList = getProjections();
        int cnt = 0;

        Projection selfProjection = getProjectionById(id);
        for(Projection singleProjection : projectionList){
            if(singleProjection == selfProjection){
                cnt++;
            }
        }

        movieReference.setAvailable(cnt > 1);

        projectionRepository.delete(getProjectionById(id));
        return true;
    }
    
}
