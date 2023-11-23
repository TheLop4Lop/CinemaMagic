package com.booster.CineMagic.Entity;

import com.booster.CineMagic.Enum.Account;
import com.booster.CineMagic.Enum.MovieFormat;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Movie")
    private Integer id;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Title")
    private String title;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Duration")
    private String duration;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Country")
    private String country;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Category")
    private String category;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Classification")
    private String classification;

    @NotNull(message = "Name is Null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Debe ser mayor o igual que 0.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "Debe ser menor o igual que 5.0")
    @Column(name = "Rating")
    private float rating;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Synopsis")
    private String synopsis;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Movie_Language")
    private String language;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Release_Year")
    private int year;

    @NotNull(message = "Name is Null")
    @NotEmpty(message = "Name is Empty")
    @Column(name = "Director")
    private String director;

    @NotNull(message = "Account is Null")
    @Column(name = "Format")
    @Enumerated(EnumType.STRING)
    private MovieFormat format;

    @NotNull(message = "Account is Null")
    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private Account type;

    public Movie() {

    }

    public Movie(Integer id, String title, String duration, String country, String category, String classification,
                 float rating, String synopsis, String language, int year, String director, MovieFormat format, Account type) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.country = country;
        this.category = category;
        this.classification = classification;
        this.rating = rating;
        this.synopsis = synopsis;
        this.language = language;
        this.year = year;
        this.director = director;
        this.format = format;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieFormat getFormat() {
        return format;
    }

    public void setFormat(MovieFormat format) {
        this.format = format;
    }

    public Account getType() {
        return type;
    }

    public void setType(Account type) {
        this.type = type;
    }
}
