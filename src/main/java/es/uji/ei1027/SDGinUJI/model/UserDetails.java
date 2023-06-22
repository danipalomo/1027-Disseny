package es.uji.ei1027.SDGinUJI.model;


public class UserDetails {
    private String username;
    private String email;
    private String password;
    private TypeUser typeUser;

    public UserDetails() {
        this.username = null;
        this.password= null;
        this.typeUser= TypeUser.UJI_MEMBER;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}

