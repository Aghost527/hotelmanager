package com.dhc.core.datasource;

import org.hibernate.SessionFactory;

public interface DynamicSessionFactory extends SessionFactory {
    
    public SessionFactory getHibernateSessionFactory();
}