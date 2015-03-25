package com.cwca.ssh.dao;

import java.util.List;

import com.cwca.ssh.pojo.Test;

/**
 * @ClassName: TestDao
 * @Description: TODO 测试持久层
 * @author liao lp-liao@foxmial.com
 * @date 2015年3月25日 下午2:21:25
 * 
 */
public interface TestDao {

	/**
	 * @Title: query
	 * @Description: TODO(作用) 查询
	 * @param @return 设定文件
	 * @return List<Test> 返回类型
	 * @throws
	 */
	public List<Test> query();

}
