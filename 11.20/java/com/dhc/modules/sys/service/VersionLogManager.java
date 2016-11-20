package com.dhc.modules.sys.service;

import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateDao;
import com.dhc.modules.sys.entity.VersionLog;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionLogManager extends EntityManager<VersionLog, Long> {

    private HibernateDao<VersionLog, Long> versionLogDao;


    /**
     * 通过注入的sessionFactory初始化默认的泛型DAO成员变量.
     */
    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        versionLogDao = new HibernateDao<VersionLog, Long>(sessionFactory, VersionLog.class);
    }

    @Override
    protected HibernateDao<VersionLog, Long> getEntityDao() {
        return versionLogDao;
    }

    /**
     * 清空所有更新日志数据
     */
    public void removeAll(){
        int reslutCount = getEntityDao().batchExecute("delete from VersionLog");
        logger.debug("清空版本更新日志：{}",reslutCount);
    }
}
