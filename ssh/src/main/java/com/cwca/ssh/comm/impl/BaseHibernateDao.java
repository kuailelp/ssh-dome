package com.cwca.ssh.comm.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cwca.ssh.comm.BaseDao;

/**
 * hibernate 泛型 dao 实现
 * 
 * @ClassName: BaseHibernateDao
 * @Description: TODO
 * @author liao lp-liao@foxmial.com
 * @date 2015年3月25日 上午11:35:36
 * 
 */
public class BaseHibernateDao<M extends java.io.Serializable, PK extends java.io.Serializable>
		implements BaseDao<M, PK> {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(BaseHibernateDao.class);

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private Transaction tx;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Transaction getTx(Session session) {
		tx = session.beginTransaction();
		return tx;
	}

	public void commit() {
		this.tx.commit();

	}

	public void rollback() {
		this.tx.rollback();
	}

	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		Session session = this.sessionFactory.getCurrentSession();
		this.getTx(session);
		return session;
	}

	@Override
	public PK save(M model) {
		// TODO Auto-generated method stub
		return (PK) getSession().save(model);
	}

	@Override
	public void saveOrUpdate(M model) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(model);
	}

	@Override
	public void update(M model) {
		// TODO Auto-generated method stub
		getSession().update(model);
	}

	@Override
	public void merge(M model) {
		// TODO Auto-generated method stub
		getSession().merge(model);
	}

	@Override
	public void deleteObject(M model) {
		// TODO Auto-generated method stub
		getSession().delete(model);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		getSession().flush();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		getSession().clear();
	}

}
