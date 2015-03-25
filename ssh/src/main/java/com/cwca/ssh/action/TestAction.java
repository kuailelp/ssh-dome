package com.cwca.ssh.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cwca.ssh.pojo.Test;
import com.cwca.ssh.service.TestService;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Namespace("/test")
@Scope(value = "prototype")
public class TestAction extends ActionSupport {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7223918081371269875L;

	@Resource
	private TestService service;

	private Test test;

	private List<Test> list = new ArrayList<Test>();

	@Action(value = "query", results = { @Result(name = "success", location = "/index.jsp") })
	public String go() {
		list = this.service.query();
		test = new Test();
		test.setId(1);
		test.setTest("ssss");
		return SUCCESS;
	}

	public List<Test> getList() {
		return list;
	}

	public void setList(List<Test> list) {
		this.list = list;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

}
