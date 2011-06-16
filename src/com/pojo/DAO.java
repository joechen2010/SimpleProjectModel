package com.pojo;

import java.util.List;

public abstract class DAO<T> extends BaseHibernateDAO{
	
	public abstract int getDataCount();
	
	public abstract List<T> findPage(String sortColumnName, boolean sortAscending, int startRow, int maxResults);

}
