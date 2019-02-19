/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class InMemoryCinemaPersistence implements CinemaPersitence{
    
    private final Map<String,Cinema> cinemas=new HashMap<>();
    

    public InMemoryCinemaPersistence() {
        //load stub data
        String functionDate = "2018-12-18 15:30";
        List<CinemaFunction> functions= new ArrayList<>();
        CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie","Action"),functionDate);
        CinemaFunction funct2 = new CinemaFunction(new Movie("The Night","Horror"),functionDate);
        functions.add(funct1);
        functions.add(funct2);
        Cinema c=new Cinema("cinemaX",functions);
        cinemas.put("cinemaX", c);
    }    

    @Override
    public void buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
        //throw new UnsupportedOperationException("Not supported yet.");
        Cinema c=cinemas.get(cinema);
        CinemaFunction cf1 = null;
        for(CinemaFunction cf:c.getFunctions()){
            if(cf.getMovie().getName().equals(movieName) && cf.getDate()==date){
                cf1=cf;
                break;
            }
        }
        cf1.buyTicket(row, col);
    }

    @Override
    public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) {
        List<CinemaFunction> funciones=new ArrayList<>();
        Cinema c=cinemas.get(cinema);
        for(CinemaFunction cf:c.getFunctions()){
            if(cf.getDate()==date){
                funciones.add(cf);
                
            }
        }
        return funciones;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public void saveCinema(Cinema c) throws CinemaPersistenceException {
        if (cinemas.containsKey(c.getName())){
            throw new CinemaPersistenceException("The given cinema already exists: "+c.getName());
        }
        else{
            cinemas.put(c.getName(),c);
        }   
    }

    @Override
    public Cinema getCinema(String name) throws CinemaPersistenceException {
        
        if (cinemas.containsKey(name)){
            Cinema ans = cinemas.get(name);
            return ans;
        }
        else{
            throw new CinemaPersistenceException("The given name dont corresponde to any cinema");
        }
    }
// forma de spring de hacer main
//    public static void main(String[] args) {
//		SpringApplication.run(CollabPaintApplication.class, args);
//	}
}
