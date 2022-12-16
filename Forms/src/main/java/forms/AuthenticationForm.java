package forms;

public class AuthenticationForm {
    final String form = "authentication";
    public String login;
    public String password;

    public String getForm() {
        return form;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationForm(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
