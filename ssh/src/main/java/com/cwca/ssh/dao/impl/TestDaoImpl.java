package com.cwca.ssh.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.cwca.ssh.comm.impl.BaseHibernateDao;
import com.cwca.ssh.dao.TestDao;
import com.cwca.ssh.pojo.Test;

@Repository
public class TestDaoImpl extends BaseHibernateDao<Test, Integer> implements
		TestDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> query() {
		// TODO Auto-generated method stub
		Criteria query;
		query = this.getSession().createCriteria(Test.class);
		return query.list();
	}
}
