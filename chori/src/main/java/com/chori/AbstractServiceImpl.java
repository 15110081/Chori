package com.chori;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public abstract class AbstractServiceImpl<Entity, ID extends Serializable>
		implements AbstractService<Entity, ID> {
	protected final Log log = LogFactory.getLog(getClass());

	private AbstractDao<Entity, ID> abstractDao;

	public AbstractServiceImpl(AbstractDao<Entity, ID> abstractDao) {
		this.abstractDao = abstractDao;
	}

	public AbstractServiceImpl() {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Entity findById(ID id) {
		log.info("getByID");

		try {
			log.debug(String.format("getting %s instance with id: %s",
					getClass(), id.toString()));
			Entity instance = abstractDao.findById(id);
			log.debug("get successful");
			return instance;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Entity> getAll() {
		log.info("getList");

		try {
			log.debug(String.format("getting all %s instance ", getClass()));
			List<Entity> ls = abstractDao.getAll();
			log.debug("get successful");
			return ls;
		} catch (Exception re) {
			log.error("get list failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Entity> search(Map<String, Object> parameterMap) {
		log.info("Search");
		try {
			log.debug(String.format("Searching by:%s", parameterMap));
			List<Entity> ls = abstractDao.search(parameterMap);
			log.debug("search successful");
			return ls;
		} catch (Exception e) {
			log.error("search failed", e);
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ID save(Entity entity) {
		log.info("save");

		try {
			log.debug(String.format("persisting %s instance", getClass()));
			ID id = abstractDao.save(entity);
			log.debug("persist successful");
			return id;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Entity entity) {
		log.info("update");

		try {
			log.debug(String.format("updating %s instance", getClass()));
			abstractDao.update(entity);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("udpate failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Entity entity) {
		log.info("delete");

		try {
			log.debug(String.format("removing %s instance", getClass()));
			abstractDao.delete(entity);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(ID id) {
		log.info("deleteById");
		try {
			abstractDao.deleteById(id);
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Entity merge(Entity entity) {
		log.debug(String.format("merging %s instance", getClass()));
		try {
			Entity result = (Entity) abstractDao.merge(entity);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

}
