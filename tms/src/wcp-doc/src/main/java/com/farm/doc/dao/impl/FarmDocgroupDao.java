package com.farm.doc.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.farm.core.sql.query.DBRule;
import com.farm.core.sql.query.DataQuery;
import com.farm.core.sql.result.DataResult;
import com.farm.core.sql.utils.HibernateSQLTools;
import com.farm.doc.dao.FarmDocgroupDaoInter;
import com.farm.doc.domain.FarmDocgroup;

/**
 * 工作小组
 * 
 * @author MAC_wd
 * 
 */
@Repository
public class FarmDocgroupDao extends HibernateSQLTools<FarmDocgroup>implements FarmDocgroupDaoInter {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFatory;

	public void deleteEntity(FarmDocgroup entity) {
		Session session = sessionFatory.getCurrentSession();
		session.delete(entity);
	}

	public int getAllListNum() {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery("select count(*) from farm_docgroup");
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	public FarmDocgroup getEntity(String id) {
		Session session = sessionFatory.getCurrentSession();
		return (FarmDocgroup) session.get(FarmDocgroup.class, id);
	}

	public FarmDocgroup insertEntity(FarmDocgroup entity) {
		Session session = sessionFatory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	public void editEntity(FarmDocgroup entity) {
		Session session = sessionFatory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Session getSession() {
		return sessionFatory.getCurrentSession();
	}

	public DataResult runSqlQuery(DataQuery query) {
		try {
			return query.search(sessionFatory.getCurrentSession());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteEntitys(List<DBRule> rules) {
		deleteSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public List<FarmDocgroup> selectEntitys(List<DBRule> rules) {
		return selectSqlFromFunction(sessionFatory.getCurrentSession(), rules);
	}

	@Override
	public void updataEntitys(Map<String, Object> values, List<DBRule> rules) {
		updataSqlFromFunction(sessionFatory.getCurrentSession(), values, rules);
	}

	public SessionFactory getSessionFatory() {
		return sessionFatory;
	}

	public void setSessionFatory(SessionFactory sessionFatory) {
		this.sessionFatory = sessionFatory;
	}

	@Override
	public int getGroupDocNum(String groupId) {
		Session session = sessionFatory.getCurrentSession();
		SQLQuery sqlquery = session.createSQLQuery("select count(*) from FARM_DOC  where STATE='1' and  DOCGROUPID=?");
		sqlquery.setString(0, groupId);
		BigInteger num = (BigInteger) sqlquery.list().get(0);
		return num.intValue();
	}

	@Override
	protected SessionFactory getSessionFactory() {
		return sessionFatory;
	}

	@Override
	protected Class<FarmDocgroup> getTypeClass() {
		return FarmDocgroup.class;
	}
}
