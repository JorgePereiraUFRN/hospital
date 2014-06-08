package br.ufrn.hospital.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    
	public static final SessionFactory session = buildSession();
	 
    private static SessionFactory buildSession() {
 
    try{
         Configuration cfg = new Configuration();
                       cfg.configure("hibernate.cfg.xml");
 
        return cfg.buildSessionFactory();
 
       }catch(Throwable b){
 
            System.out.println("Erro nas configurações do BD \n" + b);
            throw new ExceptionInInitializerError();
       }
   }
 
   public static SessionFactory getSessionFactory(){
       return session;
   }
	
	/*
	private static SessionFactory sessionFactory;  
    
    private static final ThreadLocal sessionThread;  
  
    static {  
        sessionThread = new ThreadLocal();  
        sessionFactory = new Configuration().configure().buildSessionFactory();  
    }  
      
    public static Session getSession() {  
        Session session = (Session)sessionThread.get();  
          
        if (session == null) {  
            session = sessionFactory.openSession();   
            sessionThread.set(session);  
        }  
        return session;  
    }  
      
    public static void closeSession() {  
        Session session = (Session)sessionThread.get();  
        sessionThread.set(null);  
          
        if (session != null && session.isOpen()) {  
            session.flush();  
            session.close();  
        }  
    } 
    */
}
