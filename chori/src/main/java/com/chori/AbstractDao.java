package com.chori;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface AbstractDao<Entity, ID extends Serializable> {
	Entity findById(ID id);

	List<Entity> getAll();

	List<Entity> search(Map<String, Object> parameterMap);

	ID save(Entity entity);

	void update(Entity entity);

	Entity merge(Entity entity);

	void delete(Entity entity);

	void deleteById(ID id);
}
