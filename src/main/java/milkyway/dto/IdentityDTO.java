package milkyway.dto;


public class IdentityDTO {
    private String login;
    private String identity;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public IdentityDTO() {

    }

    public IdentityDTO(String login, String identity) {

        this.login = login;
        this.identity = identity;
    }
}
