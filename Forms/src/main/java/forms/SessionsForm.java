package forms;

import Entities.Session;

import java.util.ArrayList;

public class SessionsForm {
    final String form = "sessions";
    public int film_id;
    public ArrayList<Session> sessions;

    public SessionsForm(int film_id, ArrayList<Session> sessions) {
        this.film_id = film_id;
        this.sessions = sessions;
    }

    public SessionsForm(int film_id) {
        this.film_id = film_id;
    }

    public SessionsForm() {
    }

    public String getForm() {
        return form;
    }

    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}
