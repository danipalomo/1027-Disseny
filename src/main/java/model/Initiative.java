package model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Initiative {


    private static volatile AtomicInteger idCount;

    private int id;  //primary key

    private String name;

    private String description;

    private InitiativeState state;

    private String url;

    private String results;

    private String goal;

    private int idSDG;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate finishDate;

    private enum InitiativeState {
        WIP, DONE
    }

    public static int getIdCount() {
        return idCount.get();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InitiativeState getState() {
        return state;
    }

    public void setState(InitiativeState state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getIdSDG() {
        return idSDG;
    }

    public void setIdSDG(int idSDG) {
        this.idSDG = idSDG;
    }

    public int incrementAndGetIdCount(){
        return Initiative.idCount.incrementAndGet();
    }


}
