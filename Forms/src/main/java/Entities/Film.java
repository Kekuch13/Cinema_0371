package Entities;

public class Film {
    private int film_id;
    private String title;
    private int year;
    private String genre;
    private int duration;
    private String country;

    public Film(int film_id, String title, int year, String genre, int duration, String country) {
        this.film_id = film_id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                ", country='" + country + '\'' +
                '}';
    }
}
