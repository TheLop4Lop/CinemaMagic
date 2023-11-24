package com.booster.CineMagic.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "projection")
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Movie")
    @NotNull(message = "movieId is null")
    private Movie movie;

    @NotNull(message = "Room is Null")
    @NotEmpty(message = "Room is Empty")
    @Column(name = "Room")
    private String room;

    @NotNull(message = "Hour is Null")
    @NotEmpty(message = "Hour is Empty")
    @Column(name = "Projection_Hour")
    private String hour;

    public Projection() {

    }

    public Projection(Integer id, Movie movie, String room, String hour) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.hour = hour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
