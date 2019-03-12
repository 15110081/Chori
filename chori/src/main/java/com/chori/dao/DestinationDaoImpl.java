package com.chori.dao;

import org.springframework.stereotype.Repository;

import com.chori.AbstractDaoImpl;
import com.chori.entity.Destination;

@Repository("destinationDao")
public class DestinationDaoImpl extends AbstractDaoImpl<Destination, String>
		implements DestinationDao {

}
