package com.example.demo;

import org.springframework.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TestApi {
    @Autowired
    private Klasy klasy;
    private Integer addedItemsCounter = 1;
    @GetMapping("/ping")
    public String ping()
    {
        return "Endpoint OK";
    }

    @PostMapping(value= "/activities", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void addClass(@RequestBody Activity newClass)
    {
        newClass.setId(addedItemsCounter++);
        klasy.add(newClass);
    }

    @GetMapping(value= "/activities", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Activity>> getClasses(
            @Nullable @RequestParam("nazwa") String nazwa,
            @Nullable @RequestParam("ects") Integer ects,
            @Nullable @RequestParam("sala") String sala,
            @Nullable @RequestParam("egzamin") String egzamin
            )
    {
        return ResponseEntity.ok(klasy.get(nazwa, ects, sala, egzamin));
    }

    @DeleteMapping(value= "/activities")
    public void deleteClasses()
    {
        klasy.clean();
    }

    @GetMapping(value= "/activities/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Activity> activitiesList(@PathVariable(value="id") String id)
    {
        Activity res = klasy.getById(Integer.valueOf(id));
        if(res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value= "/activities/{id}")
    public ResponseEntity activitiesList(@PathVariable(value="id") Integer id)
    {
        boolean res = klasy.clean(id);
        if (res) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
