package com.chori;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings("unchecked")
@Repository
public abstract class AbstractDaoImpl<Entity, ID extends Serializable>
		implements AbstractDao<Entity, ID> {

	// protected final Log log = LogFactory.getLog(getClass());
	protected final Logger logger = Logger.getLogger(getClass());
	@Autowired
	protected SessionFactory sessionFactory;

	protected Class<? extends Entity> daoType;

	public AbstractDaoImpl() {
		Type t = (Type) getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class) pt.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		logger.info("getSession");
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			logger.fatal("System Error:" + e);
			throw e;
		}
	}

	public Entity findById(ID id) {
		logger.info("getByID");

		try {
			logger.debug(String.format("getting %s instance with id: %s",
					getClass(), id.toString()));
			Entity instance = (Entity) getSession().get(daoType, id);
			logger.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			logger.error("get list failed", re);
			throw re;
		}
	}

	public List<Entity> getAll() {
		logger.info("getList");

		try {
			logger.debug(String.format("getting all %s instance ", getClass()));
			Criteria criteria = createEntityCriteria();
			logger.debug("get successful");
			return (List<Entity>) criteria.list();
		} catch (RuntimeException re) {
			logger.error("get failed", re);
			throw re;
		}
	}

	public List<Entity> search(Map<String, Object> parameterMap) {
		logger.info("search");
		try {
			logger.debug(String.format("Searching by:%s", parameterMap));
			// TODO For search purpose
			Criteria criteria = getSession().createCriteria(daoType);
			Set<String> fieldNames = parameterMap.keySet();
			for (String field : fieldNames) {
				criteria.add(Restrictions.ilike(field, parameterMap.get(field)));
			}
			logger.debug("search successful");
			return criteria.list();
		} catch (Exception e) {
			logger.error("search failed", e);
			throw e;
		}
	}

	public ID save(Entity entity) {
		logger.info("save");

		try {
			logger.debug(String.format("persisting %s instance", getClass()));

			ID id = (ID) getSession().save(entity);
			logger.debug("persist successful");
			return id;
		} catch (Exception re) {
			logger.error("persist failed", re);
			throw re;
		}
	}

	public void update(Entity entity) {
		logger.info("update");

		try {
			logger.debug(String.format("updating %s instance", getClass()));
			getSession().update(entity);
			logger.debug("update successful");
		} catch (RuntimeException re) {
			logger.error("udpate failed", re);
			throw re;
		}
	}

	public void delete(Entity entity) {
		logger.info("delete");

		try {
			logger.debug(String.format("removing %s instance", getClass()));
			getSession().delete(entity);
			logger.debug("remove successful");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public void deleteById(ID id) {
		logger.info("deleteById");
		try {
			Entity entity = findById(id);
			if (entity != null)
				delete(entity);
			logger.info(getClass() + " given by id:" + id + "did not found");
		} catch (RuntimeException re) {
			logger.error("remove failed", re);
			throw re;
		}
	}

	public Entity merge(Entity entity) {
		logger.info("merge");

		try {
			logger.debug(String.format("merging %s instance", getClass()));
			Entity result = (Entity) getSession().merge(entity);
			logger.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			throw re;
		}
	}

	protected Criteria createEntityCriteria() {
		logger.info("createEntityCriteria");
		try {
			return getSession().createCriteria(daoType);
		} catch (Exception e) {
			logger.error(String.format(
					"create criterial has error: %s in class: %s",
					e.getMessage(), daoType));
			throw e;
		}
	}

}
