package com.pojo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 * Data access object (DAO) for domain model class UserMaster.
 * 
 * @see demo.hibernate.UserMaster
 * @author MyEclipse Persistence Tools
 */

public class UserInfoDAO extends DAO {
	private static final Log log = LogFactory.getLog(UserInfoDAO.class);

	

	public List findAll() {

		try {
			String queryString = "from UserInfo";
			Session session = this.getSession();
			Transaction tx = session.beginTransaction();
			Query queryObject = getSession().createQuery(queryString);
			List list = queryObject.list();
			tx.commit();
			session.close();
			return list;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
    @SuppressWarnings("unchecked")
    public List<UserInfo> findPage(String sortColumnName, boolean sortAscending, int startRow, int maxResults) {
        try {
            String queryString = "select c from UserInfo c order by c." + sortColumnName + " " + (sortAscending ? "asc" : "desc");
            return getSession().createQuery(queryString).setFirstResult(startRow).setMaxResults(maxResults).list();
        } catch (RuntimeException re) {
            throw re;
        }
    }
	
	
	public List getPagedData(int start, int page) {
		  try {
		    Criteria criteria = getSession().createCriteria(T.class);
		    //Build Criteria object here
		    criteria.setFirstResult(start);
		    criteria.setMaxResults(page);
		    return criteria.list();
		  } catch (HibernateException hibernateException) {
		    //do something here with the exception
		  }
		  return null;
		}      
		  
		    
		    
		  public int getDataCount() {
		    Criteria criteria = getSession().createCriteria(UserInfo.class);
		    criteria.setProjection(Projections.rowCount());

		    // Build Criteria object here
		    Number nuofRecords = ((Number) criteria.uniqueResult());
		    return nuofRecords == null ? 0 : nuofRecords.intValue();
		  }      

	
}