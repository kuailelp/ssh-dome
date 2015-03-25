package com.cwca.ssh.comm;

public interface BaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {

	public PK save(M model);

	public void saveOrUpdate(M model);

	public void update(M model);

	public void merge(M model);

	public void deleteObject(M model);

	public void flush();

	public void clear();

}
