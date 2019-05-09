package com.framework.loippi.service;

import com.framework.loippi.entity.CallRoll;
import java.util.List;
/**
 * SERVICE - CallRoll(点名)
 * 
 * @author wmj
 * @version 2.0
 */
public interface CallRollService  extends GenericService<CallRoll, Long> {
	public	List<CallRoll> findListByPage(Object parameter);
	public  List<CallRoll>   selectStatus();
}
