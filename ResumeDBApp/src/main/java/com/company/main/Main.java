/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.company.main;

import com.company.entity.Country;
import com.company.dao.inter.CountryDaoInter;

/**
 *
 * @author elxan
 * DAO--Data Access Object
 */
public class Main {


    public static void main(String[] args) throws Exception {
        CountryDaoInter countryDao = Context.instanceCountryDao();
        
       Country c = new Country(4, "Russia", "Russian");
        countryDao.updateCountry(c);
      
       
    }
}
