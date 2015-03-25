package com.cwca.ssh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwca.ssh.dao.TestDao;
import com.cwca.ssh.pojo.Test;
import com.cwca.ssh.service.TestService;

@Component
public class TestServiceImpl implements TestService {

	@Resource
	private TestDao dao;

	@Override
	public List<Test> query() {
		// TODO Auto-generated method stub
		return this.dao.query();
		// return null;
	}

}
