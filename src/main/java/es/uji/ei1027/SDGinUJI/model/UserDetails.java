package es.uji.ei1027.SDGinUJI.model;


public class UserDetails {
    String username;
    String password;
    TypeUser typeUser;

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

