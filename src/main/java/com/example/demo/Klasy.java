package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class Klasy {
    private List<Activity> klasy = new ArrayList<>();

    public void add(Activity cl)
    {
        klasy.add(cl);
    }

    public Activity getById(int id)
    {
        for(Activity el : klasy)
        {
            if (el.getId().equals(id)) return el;
        }
        return null;
    }

    public List<Activity> get(String nazwa, Integer ects, String sala, String egzamin) {
        List<Activity> filtruj = new ArrayList<>(klasy);

        if(nazwa !=null) {
            filtruj = filtruj.stream().filter(el -> el.getNazwa().equals(nazwa)).collect(Collectors.toList());
        }
        if(ects!=null) {
            filtruj = filtruj.stream().filter(el -> el.getEcts().equals(ects)).collect(Collectors.toList());
        }
        if(sala !=null) {
            filtruj = filtruj.stream().filter(el -> el.getSala().equals(sala)).collect(Collectors.toList());
        }
        if(egzamin !=null) {
            Boolean czyEgzamin;
            if(egzamin.toUpperCase().equals("TRUE") || egzamin.toUpperCase().equals("OK")) {
                czyEgzamin = true;
            } else {
                czyEgzamin = false;
            }
            filtruj = filtruj.stream().filter(el -> el.getCzyEgzamin()== czyEgzamin).collect(Collectors.toList());
        }

        return filtruj;
    }

    public void clean()
    {
        klasy.clear();
    }

    public boolean clean(Integer id)
    {
        return klasy.removeIf(el -> el.getId()==id);
    }

}
