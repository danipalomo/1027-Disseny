package es.uji.ei1027.SDGinUJI.model;

public class Participa {

    private String ujiMemberId;

    private int idInitiative;

    private boolean responsible;

    public String getUjiMemberId() {
        return ujiMemberId;
    }

    public void setUjiMemberId(String ujiMemberId) {
        this.ujiMemberId = ujiMemberId;
    }

    public void setIdInitiative(int idInitiative) {
        this.idInitiative = idInitiative;
    }

    public int getIdInitiative() {
        return idInitiative;
    }

    public boolean isResponsible() {
        return responsible;
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

}
