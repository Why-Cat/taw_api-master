package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

// @Value dodaje gettery i settery
// Można użyć @Data - zawiera więcej
@JsonSerialize
@Data
public class ClassDto {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nazwa")
    String name;

    @JsonProperty("ects")
    Integer ects;

    @JsonProperty("sala")
    String room;

    @JsonProperty("egzamin")
    Boolean isExam;

    public ClassDto(Integer id, String name, Integer ects, String room, Boolean isExam) {
        this.id = id;
        this.name = name;
        this.ects = ects;
        this.room = room;
        this.isExam = isExam;
    }
}
