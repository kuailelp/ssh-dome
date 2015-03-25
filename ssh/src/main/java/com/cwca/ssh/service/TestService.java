package com.cwca.ssh.service;

import java.util.List;

import com.cwca.ssh.pojo.Test;

/**
 * @ClassName: TestService
 * @Description: TODO 测试业务逻辑
 * @author liao lp-liao@foxmial.com
 * @date 2015年3月25日 下午2:19:24
 * 
 */
public interface TestService {

	/**
	 * @Title: query
	 * @Description: TODO(作用) 查询
	 * @param @return 设定文件
	 * @return List<Test> 返回类型
	 * @throws
	 */
	public List<Test> query();

}
