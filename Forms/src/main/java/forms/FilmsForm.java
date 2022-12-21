package forms;

import Entities.Film;

import java.util.ArrayList;

public class FilmsForm {
    public final String form = "FilmsList";

    public ArrayList<Film> films;

    public String getForm() {
        return form;
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public FilmsForm(ArrayList<Film> films) {
        this.films = films;
    }
}
