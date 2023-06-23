package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;


@JsonSerialize
@Data
public class Activity {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nazwa")
    String nazwa;

    @JsonProperty("ects")
    Integer ects;

    @JsonProperty("sala")
    String sala;

    @JsonProperty("egzamin")
    Boolean czyEgzamin;

    public Activity(Integer id, String nazwa, Integer ects, String sala, Boolean czyEgzamin) {
        this.id = id;
        this.nazwa = nazwa;
        this.ects = ects;
        this.sala = sala;
        this.czyEgzamin = czyEgzamin;
    }
}
