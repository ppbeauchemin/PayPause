package com.ziooo.dao;

import java.util.List;

import com.ziooo.model.Type;

public interface TypeDAO {

	Type createType(String name);

	List<Type> getAllTypes();

	Type getTypeById(long idType);

	int updateType(Type type);

	int deleteType(Type type);

}
