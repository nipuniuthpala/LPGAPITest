package models;

import com.fasterxml.jackson.annotation.JsonProperty;
public class City {

    public Integer getId() {
        return id;
    }

    public String title() {
        return title;
    }
    @JsonProperty("title")
    private String title;
    @JsonProperty("id")
    private Integer id;

}
