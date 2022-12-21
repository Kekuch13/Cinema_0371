package Entities;

public class Film {
    private int film_id;
    private String title;
    private int year;
    private String genre;
    private int duration;
    private String country;

    public Film() {
    }

    public Film(int film_id, String title, int year, String genre, int duration, String country) {
        this.film_id = film_id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.country = country;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
