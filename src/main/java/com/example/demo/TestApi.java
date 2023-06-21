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
    private ClassesDB classesDB;
    private Integer addedItemsCounter = 1;
    @GetMapping("/ping")
    public String ping()
    {
        return "Endpoint OK";
    }

    @PostMapping(value="/zajecia", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void addClass(@RequestBody ClassDto newClass)
    {
        newClass.setId(addedItemsCounter++);
        classesDB.add(newClass);
    }

    @GetMapping(value="/zajecia", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClassDto>> getClasses(
            @Nullable @RequestParam("nazwa") String name,
            @Nullable @RequestParam("ects") Integer ects,
            @Nullable @RequestParam("sala") String room,
            @Nullable @RequestParam("egzamin") String exam
            )
    {
        return ResponseEntity.ok(classesDB.get(name, ects, room, exam));
    }

    @DeleteMapping(value= "/zajecia")
    public void deleteClasses()
    {
        classesDB.clean();
    }

    @GetMapping(value="/zajecia/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClassDto> activitiesList(@PathVariable(value="id") String id)
    {
        ClassDto res = classesDB.getById(Integer.valueOf(id));
        if(res != null) {
            return ResponseEntity.ok(res);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value="/zajecia/{id}")
    public ResponseEntity activitiesList(@PathVariable(value="id") Integer id)
    {
        boolean res = classesDB.clean(id);
        if (res) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
