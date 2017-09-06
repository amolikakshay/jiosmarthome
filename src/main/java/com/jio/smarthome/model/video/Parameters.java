
package com.jio.smarthome.model.video;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "language",
    "movie",
    "movie-genre",
    "music-genre",
    "person",
    "service",
    "song",
    "trailer",
    "video",
    "year"
})
public class Parameters implements Serializable
{

    @JsonProperty("language")
    private String language;
    @JsonProperty("movie")
    private String movie;
    @JsonProperty("movie-genre")
    private String movieGenre;
    @JsonProperty("music-genre")
    private String musicGenre;
    @JsonProperty("person")
    private List<Object> person = null;
    @JsonProperty("service")
    private String service;
    @JsonProperty("song")
    private String song;
    @JsonProperty("trailer")
    private String trailer;
    @JsonProperty("video")
    private String video;
    @JsonProperty("year")
    private String year;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1955485796342629289L;

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("movie")
    public String getMovie() {
        return movie;
    }

    @JsonProperty("movie")
    public void setMovie(String movie) {
        this.movie = movie;
    }

    @JsonProperty("movie-genre")
    public String getMovieGenre() {
        return movieGenre;
    }

    @JsonProperty("movie-genre")
    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    @JsonProperty("music-genre")
    public String getMusicGenre() {
        return musicGenre;
    }

    @JsonProperty("music-genre")
    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    @JsonProperty("person")
    public List<Object> getPerson() {
        return person;
    }

    @JsonProperty("person")
    public void setPerson(List<Object> person) {
        this.person = person;
    }

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty("song")
    public String getSong() {
        return song;
    }

    @JsonProperty("song")
    public void setSong(String song) {
        this.song = song;
    }

    @JsonProperty("trailer")
    public String getTrailer() {
        return trailer;
    }

    @JsonProperty("trailer")
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @JsonProperty("video")
    public String getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(String video) {
        this.video = video;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
