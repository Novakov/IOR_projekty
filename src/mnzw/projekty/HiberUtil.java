/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnzw.projekty;

import java.io.File;

import mnzw.projekty.model.JezykProgramowania;
import mnzw.projekty.model.Jezyki;
import mnzw.projekty.model.Kierownik;
import mnzw.projekty.model.Osoba;
import mnzw.projekty.model.Programista;
import mnzw.projekty.model.Projekt;
import mnzw.projekty.model.Zapotrzebowanie;
import mnzw.projekty.model.Zatrudnienie;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author LabHiber
 */
public final class HiberUtil {
    
    public enum Mapping {
       XML, ANN;
    } 
    
    public static SessionFactory getSessionFactory(Mapping mapping) {
     
        switch(mapping){
            case XML:
                return(getXMLSessionFactory());
            case ANN:
                return(getANNSessionFactory());
            default:
               return(getXMLSessionFactory());
        }  
    }
  
    public static SessionFactory getXMLSessionFactory() {
        try {
            File mappingDir = new File("src\\mnzw\\projekty\\mapowanie");
            Configuration config = new Configuration().configure();
            
            config.setProperty("hibernate.show_sql", "false");
            config.addDirectory(mappingDir);
            
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = registryBuilder.build();
                        
            SessionFactory sf = config.buildSessionFactory(serviceRegistry);
            
            return (sf);
        }
        catch (Throwable ex) {
          
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    } 
    
    public static SessionFactory getANNSessionFactory() {
        try {
            Configuration config = new Configuration().configure();
            config.setProperty("hibernate.show_sql", "false");
                      
            config.addAnnotatedClass(Jezyki.class)
            .addAnnotatedClass(JezykProgramowania.class)
            .addAnnotatedClass(Osoba.class)
            .addAnnotatedClass(Kierownik.class)
            .addAnnotatedClass(Programista.class)
            .addAnnotatedClass(Projekt.class)
            .addAnnotatedClass(Zapotrzebowanie.class)
            .addAnnotatedClass(Zatrudnienie.class);
            
            config.setProperty("hibernate.show_sql", "false");
            //config.setProperty("hibernate.hbm2ddl.auto", "none");
            
            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
            registryBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = registryBuilder.build();
                        
            SessionFactory sf = config.buildSessionFactory(serviceRegistry);
            
            return (sf);
        }
        catch (Throwable ex) {
          
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
