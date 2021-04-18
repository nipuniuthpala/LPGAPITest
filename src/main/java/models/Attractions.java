package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attractions {

    public Integer getId() {
        return id;
    }
    public String getCityId() {
        return cityId;
    }

    public String title() {
        return title;
    }
    public String type() {
        return type;
    }

    public double rating() {
        return rating;
    }
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("cityId")
    public String cityId;
    @JsonProperty("title")
    public String title;
    @JsonProperty("type")
    public String type;
    @JsonProperty("rating")
    public double rating;
}
