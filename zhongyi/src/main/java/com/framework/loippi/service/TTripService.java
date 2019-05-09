package com.framework.loippi.service;

import java.util.List;

import com.framework.loippi.entity.TTrip;

/**
 * SERVICE - TTrip(行程)
 * 
 * @author longbh
 * @version 2.0
 */
public interface TTripService  extends GenericService<TTrip, Long> {
	  List<TTrip> findListTrip();
}
