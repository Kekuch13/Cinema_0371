package forms;

public class AuthenticationForm {
    final String form = "authentication";
    public String login;
    public String password;
    public boolean isValid;

    public String role;

    public AuthenticationForm(String login, String password, boolean isValid) {
        this.login = login;
        this.password = password;
        this.isValid = isValid;
    }

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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthenticationForm{" +
                "form='" + form + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
