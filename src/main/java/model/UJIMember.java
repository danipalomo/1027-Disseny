package model;

public class UJIMember implements Comparable<UJIMember>{

    private String email; //primary key

    private String name;

    private String type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(UJIMember ujiMember){
        return this.email.compareTo(ujiMember.getEmail());
    }
}
