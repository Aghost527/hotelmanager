/**
 *   
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 */
package com.dhc.modules.sys.service;

import com.dhc.common.exception.DaoException;
import com.dhc.common.exception.SystemException;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateDao;
import com.dhc.common.orm.hibernate.Parameter;
import com.dhc.modules.sys.entity.Log;
import com.dhc.utils.AppConstants;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class LogManager extends
        EntityManager<Log, Long> {

    private HibernateDao<Log, Long> logDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        logDao = new HibernateDao<Log, Long>(
                sessionFactory, Log.class);
    }

    @Override
    protected HibernateDao<Log, Long> getEntityDao() {
        return logDao;
    }

    /**
     * 清空所有日志
     * @throws DaoException
     * @throws SystemException
     */
    public void removeAll() throws DaoException,SystemException{
        int reslutCount = getEntityDao().batchExecute("delete from Log ");
        logger.debug("清空日志：{}",reslutCount);
    }

    /**
     * 清空有效期之外的日志
     * @param  day 保留时间 （天）
     * @throws DaoException
     * @throws SystemException
     */
    public void clearInvalidLog(int day) throws DaoException,SystemException{
        if(day <0){
            throw new SystemException("参数[day]不合法，需要大于0.输入为："+day);
        }
        Parameter parameter = null;
        String sql= "delete from Log l where datediff(now(),l.operTime)>"+day;
        logDao.createQuery(sql, parameter).executeUpdate();
    }
}