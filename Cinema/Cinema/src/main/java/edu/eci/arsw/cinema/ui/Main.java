/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.ui;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.cinema.services.CinemaServices;

/**
 *
 * @author 2108263
 */
public class Main {
    public static void main(String[] args) {		
    	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    	CinemaServices cs = ac.getBean(CinemaServices.class);
        //System.out.println(cs);
	}
}
