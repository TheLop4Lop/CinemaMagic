package com.booster.CineMagic;

import com.booster.CineMagic.Entity.Movie;
import com.booster.CineMagic.Entity.Projection;
import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieFormat;
import com.booster.CineMagic.Enum.ProjectionCriteria;
import com.booster.CineMagic.Repository.IProjectionRepository;
import com.booster.CineMagic.Service.ProjectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProjectionServiceTest {
    public ProjectionServiceTest() {
    }

    @Mock
    private IProjectionRepository projectionRepository;
    @InjectMocks
    private ProjectionService projectionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        List<Projection> projectionListTest = new ArrayList<>();
        Movie movieTest = new Movie(1, "Paddington", "95 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 42, true);
        Projection projectionTest = new Projection(1, movieTest, "2A", "15:00");
        projectionListTest.add(projectionTest);

        when(projectionRepository.findAll()).thenReturn(projectionListTest);
        when(projectionRepository.findById(1)).thenReturn(Optional.of(projectionTest));
        when(projectionRepository.existsById(1)).thenReturn(true);
    }

    @Test
    void testGetProjections(){
        List<Projection> projectionList = projectionService.getProjections();

        assertTrue(projectionList != null && !projectionList.isEmpty());
    }

    @Test
    void testGetProjectionsByCriteria(){
        List<Projection> projectionList = projectionService.getProjectionsByCriteria(ProjectionCriteria.ROOM, "2A");

        assertTrue(projectionList != null && !projectionList.isEmpty());
    }

    @Test
    void testGetProjectionById(){
        Projection projection = projectionService.getProjectionById(1);

        assertNotNull(projection);
    }

    @Test
    void testCheckUsageOfRoomHour(){
        Movie checkMovie = new Movie(1, "Paddington 2", "97 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear 2", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 78, true);
        Projection checkProjection = new Projection(1, checkMovie, "2A", "15:00");

        Projection projection = projectionService.checkUsageOfRoomHour(checkProjection);

        assertNotNull(projection);
    }

    @Test
    void testAddNewProjection(){
        Movie newMovie = new Movie(1, "Paddington 2", "97 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear 2", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 78, true);
        Projection newProjection = new Projection(1, newMovie, "3A", "16:00");

        when(projectionRepository.save(newProjection)).thenReturn(newProjection);
        Projection actualProjection = projectionService.addNewProjection(newProjection);

        assertEquals(newProjection, actualProjection);
    }

    @Test
    void testModifyProjectionById(){
        Movie modifyMovie = new Movie(1, "Paddington 2", "97 minutes", "UK", "Adventure",
                "PG", 4, "And adventure with a Bear 2", "English", 2014,
                "Paul King", MovieFormat.SUBTITLE, Account.REGULAR, 78, true);
        Projection modifyProjection = new Projection(1, modifyMovie, "3A", "16:00");

        when(projectionRepository.save(any(Projection.class))).thenReturn(modifyProjection);
        Projection actualProjection = projectionService.modifyProjectionById(1, modifyProjection);

        assertEquals(modifyProjection, actualProjection);
    }

    @Test
    void testDeleteProjectionById(){
        boolean actualBool = projectionService.deleteProjectionById(1);

        assertTrue(actualBool);
    }

}
