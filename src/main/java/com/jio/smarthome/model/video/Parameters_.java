
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
    "song",
    "music-genre",
    "movie",
    "year",
    "language.original",
    "movie.original",
    "video.original",
    "language",
    "video",
    "service.original",
    "music-genre.original",
    "trailer",
    "person.original",
    "service",
    "person",
    "trailer.original",
    "song.original",
    "movie-genre.original",
    "year.original",
    "movie-genre"
})
public class Parameters_ implements Serializable
{

    @JsonProperty("song")
    private String song;
    @JsonProperty("music-genre")
    private String musicGenre;
    @JsonProperty("movie")
    private String movie;
    @JsonProperty("year")
    private String year;
    @JsonProperty("language.original")
    private String languageOriginal;
    @JsonProperty("movie.original")
    private String movieOriginal;
    @JsonProperty("video.original")
    private String videoOriginal;
    @JsonProperty("language")
    private String language;
    @JsonProperty("video")
    private String video;
    @JsonProperty("service.original")
    private String serviceOriginal;
    @JsonProperty("music-genre.original")
    private String musicGenreOriginal;
    @JsonProperty("trailer")
    private String trailer;
    @JsonProperty("person.original")
    private String personOriginal;
    @JsonProperty("service")
    private String service;
    @JsonProperty("person")
    private List<Object> person = null;
    @JsonProperty("trailer.original")
    private String trailerOriginal;
    @JsonProperty("song.original")
    private String songOriginal;
    @JsonProperty("movie-genre.original")
    private String movieGenreOriginal;
    @JsonProperty("year.original")
    private String yearOriginal;
    @JsonProperty("movie-genre")
    private String movieGenre;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5252357031648874745L;

    @JsonProperty("song")
    public String getSong() {
        return song;
    }

    @JsonProperty("song")
    public void setSong(String song) {
        this.song = song;
    }

    @JsonProperty("music-genre")
    public String getMusicGenre() {
        return musicGenre;
    }

    @JsonProperty("music-genre")
    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    @JsonProperty("movie")
    public String getMovie() {
        return movie;
    }

    @JsonProperty("movie")
    public void setMovie(String movie) {
        this.movie = movie;
    }

    @JsonProperty("year")
    public String getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(String year) {
        this.year = year;
    }

    @JsonProperty("language.original")
    public String getLanguageOriginal() {
        return languageOriginal;
    }

    @JsonProperty("language.original")
    public void setLanguageOriginal(String languageOriginal) {
        this.languageOriginal = languageOriginal;
    }

    @JsonProperty("movie.original")
    public String getMovieOriginal() {
        return movieOriginal;
    }

    @JsonProperty("movie.original")
    public void setMovieOriginal(String movieOriginal) {
        this.movieOriginal = movieOriginal;
    }

    @JsonProperty("video.original")
    public String getVideoOriginal() {
        return videoOriginal;
    }

    @JsonProperty("video.original")
    public void setVideoOriginal(String videoOriginal) {
        this.videoOriginal = videoOriginal;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("video")
    public String getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(String video) {
        this.video = video;
    }

    @JsonProperty("service.original")
    public String getServiceOriginal() {
        return serviceOriginal;
    }

    @JsonProperty("service.original")
    public void setServiceOriginal(String serviceOriginal) {
        this.serviceOriginal = serviceOriginal;
    }

    @JsonProperty("music-genre.original")
    public String getMusicGenreOriginal() {
        return musicGenreOriginal;
    }

    @JsonProperty("music-genre.original")
    public void setMusicGenreOriginal(String musicGenreOriginal) {
        this.musicGenreOriginal = musicGenreOriginal;
    }

    @JsonProperty("trailer")
    public String getTrailer() {
        return trailer;
    }

    @JsonProperty("trailer")
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @JsonProperty("person.original")
    public String getPersonOriginal() {
        return personOriginal;
    }

    @JsonProperty("person.original")
    public void setPersonOriginal(String personOriginal) {
        this.personOriginal = personOriginal;
    }

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty("person")
    public List<Object> getPerson() {
        return person;
    }

    @JsonProperty("person")
    public void setPerson(List<Object> person) {
        this.person = person;
    }

    @JsonProperty("trailer.original")
    public String getTrailerOriginal() {
        return trailerOriginal;
    }

    @JsonProperty("trailer.original")
    public void setTrailerOriginal(String trailerOriginal) {
        this.trailerOriginal = trailerOriginal;
    }

    @JsonProperty("song.original")
    public String getSongOriginal() {
        return songOriginal;
    }

    @JsonProperty("song.original")
    public void setSongOriginal(String songOriginal) {
        this.songOriginal = songOriginal;
    }

    @JsonProperty("movie-genre.original")
    public String getMovieGenreOriginal() {
        return movieGenreOriginal;
    }

    @JsonProperty("movie-genre.original")
    public void setMovieGenreOriginal(String movieGenreOriginal) {
        this.movieGenreOriginal = movieGenreOriginal;
    }

    @JsonProperty("year.original")
    public String getYearOriginal() {
        return yearOriginal;
    }

    @JsonProperty("year.original")
    public void setYearOriginal(String yearOriginal) {
        this.yearOriginal = yearOriginal;
    }

    @JsonProperty("movie-genre")
    public String getMovieGenre() {
        return movieGenre;
    }

    @JsonProperty("movie-genre")
    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
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
