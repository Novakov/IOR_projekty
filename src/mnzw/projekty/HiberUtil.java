/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnzw.projekty;

import java.io.File;
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
       XML;
    } 
    
    public static SessionFactory getSessionFactory(Mapping mapping) {
     
        switch(mapping){
            case XML:
                return(getXMLSessionFactory());         
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
}
