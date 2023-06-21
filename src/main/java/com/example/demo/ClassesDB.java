package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class ClassesDB {
    private List<ClassDto> classes = new ArrayList<>();

    public void add(ClassDto cl)
    {
        classes.add(cl);
    }

    public ClassDto getById(int id)
    {
        for(ClassDto el : classes)
        {
            if (el.getId().equals(id)) return el;
        }
        return null;
    }

    public List<ClassDto> get(String name, Integer ects, String room, String exam) {
        List<ClassDto> filtered = new ArrayList<>(classes);

        if(name!=null) {
            filtered = filtered.stream().filter(el -> el.getName().equals(name)).collect(Collectors.toList());
        }
        if(ects!=null) {
            filtered = filtered.stream().filter(el -> el.getEcts().equals(ects)).collect(Collectors.toList());
        }
        if(room!=null) {
            filtered = filtered.stream().filter(el -> el.getRoom().equals(room)).collect(Collectors.toList());
        }
        if(exam!=null) {
            Boolean isExamRequrired;
            if(exam.toUpperCase().equals("TRUE") || exam.toUpperCase().equals("OK")) {
                isExamRequrired = true;
            } else {
                isExamRequrired = false;
            }
            filtered = filtered.stream().filter(el -> el.getIsExam()==isExamRequrired).collect(Collectors.toList());
        }

        return filtered;
    }

    public void clean()
    {
        classes.clear();
    }

    public boolean clean(Integer id)
    {
        return classes.removeIf(el -> el.getId()==id);
    }

}
