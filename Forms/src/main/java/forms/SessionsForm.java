package forms;

import Entities.Session;

import java.util.ArrayList;

public class SessionsForm {
    private final String form = "sessions";
    private int film_id;
    private ArrayList<String> hall_name;
    private int duration;
    private ArrayList<Session> sessions;

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

    public ArrayList<String> getHall_name() {
        return hall_name;
    }

    public void setHall_name(ArrayList<String> hall_name) {
        this.hall_name = hall_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
