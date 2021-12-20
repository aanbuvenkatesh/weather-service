package io.aanbuvenkatesh.weather.model;

public class Prediction {

    private Integer id;
    private String nature;
    private String description;

    public Prediction(Integer id, String nature, String description) {
        this.id = id;
        this.nature = nature;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
