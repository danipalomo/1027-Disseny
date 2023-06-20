package es.uji.ei1027.SDGinUJI.model;

public class SDG {

    private int idSDG; //primary key

    private String name;

    private String description;

    private String urlImage;

    public int getIdSDG() {
        return idSDG;
    }

    public void setIdSDG(int idSDG) {
        this.idSDG = idSDG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
