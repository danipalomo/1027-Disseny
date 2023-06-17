package model;

public class Participates {


    private int idInitiative;  //Initiative.id

    private String idUJIMember;  //UJIMember.email

    private boolean isResponsible;

    public int getIdInitiative() {
        return idInitiative;
    }

    public void setIdInitiative(int idInitiative) {
        this.idInitiative = idInitiative;
    }

    public String getIdUJIMember() {
        return idUJIMember;
    }

    public void setIdUJIMember(String idUJIMember) {
        this.idUJIMember = idUJIMember;
    }

    public boolean isResponsible() {
        return isResponsible;
    }

    public void setResponsible(boolean responsible) {
        isResponsible = responsible;
    }

}
