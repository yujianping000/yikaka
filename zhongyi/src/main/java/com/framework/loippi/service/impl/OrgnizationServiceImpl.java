package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.controller.app.model.AppCitys;
import com.framework.loippi.dao.OrgnizationDao;
import com.framework.loippi.entity.Area;
import com.framework.loippi.entity.Orgnization;
import com.framework.loippi.service.OrgnizationService;

import java.util.ArrayList;
import java.util.List;
/**
 * SERVICE - Orgnization(机构)
 * 
 * @author wgb
 * @version 2.0
 */
@Service
public class OrgnizationServiceImpl extends GenericServiceImpl<Orgnization, Long> implements OrgnizationService {
	
	@Autowired
	private OrgnizationDao orgnizationDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(orgnizationDao);
	}

	public List<Orgnization> findListByPage(Object parameter){
		return orgnizationDao.findListByPage(parameter);
	}
	
	public List<Orgnization> findListByPage1(Object parameter){
		return orgnizationDao.findListByPage1(parameter);
	}
	public List getGroup(List<Area> cityss) {
			List listA=new ArrayList();
			List listB=new ArrayList();
			List listC=new ArrayList();
			List listD=new ArrayList();
			List listE=new ArrayList();
			List listF=new ArrayList();
			List listG=new ArrayList();
			List listH=new ArrayList();
			List listI=new ArrayList();
			List listJ=new ArrayList();
			List listK=new ArrayList();
			List listL=new ArrayList();
			
			List listM=new ArrayList();
			List listN=new ArrayList();
			List listO=new ArrayList();
			List listP=new ArrayList();
			List listQ=new ArrayList();
			List listR=new ArrayList();
			List listS=new ArrayList();
			List listT=new ArrayList();
			
			List listU=new ArrayList();
			List listV=new ArrayList();
			List listY=new ArrayList();
			List listX=new ArrayList();
			List listW=new ArrayList();
			List listZ=new ArrayList();
			
			for (Area citys : cityss) {
				if("A".equals(citys.getLetter())){
					listA.add(citys.getName());
				}
				else if("B".equals(citys.getLetter())){
					listB.add(citys.getName());
				}else if("C".equals(citys.getLetter())){
					listC.add(citys.getName());
				}else if("D".equals(citys.getLetter())){
					listD.add(citys.getName());
				}else if("E".equals(citys.getLetter())){
					listE.add(citys.getName());
				}else if("F".equals(citys.getLetter())){
					listF.add(citys.getName());
				}else if("G".equals(citys.getLetter())){
					listG.add(citys.getName());
				}else if("H".equals(citys.getLetter())){
					listH.add(citys.getName());
				}
				else if("I".equals(citys.getLetter())){
					listI.add(citys.getName());
				}else if("J".equals(citys.getLetter())){
					listJ.add(citys.getName());
				
				}else if("K".equals(citys.getLetter())){
					listK.add(citys.getName());
				}else if("L".equals(citys.getLetter())){
					listL.add(citys.getName());
				}else if("M".equals(citys.getLetter())){
					listM.add(citys.getName());
				}else if("N".equals(citys.getLetter())){
					listN.add(citys.getName());
				}else if("O".equals(citys.getLetter())){
					listO.add(citys.getName());
				}else if("P".equals(citys.getLetter())){
					listP.add(citys.getName());
				}else if("Q".equals(citys.getLetter())){
					listQ.add(citys.getName());
				}else if("R".equals(citys.getLetter())){
					listR.add(citys.getName());
				}else if("S".equals(citys.getLetter())){
					listS.add(citys.getName());
				}else if("T".equals(citys.getLetter())){
					listT.add(citys.getName());
				
				}else if("U".equals(citys.getLetter())){
					listU.add(citys.getName());
				}else if("V".equals(citys.getLetter())){
					listV.add(citys.getName());
				}else if("Y".equals(citys.getLetter())){
					listY.add(citys.getName());
				}else if("W".equals(citys.getLetter())){
					listW.add(citys.getName());
				}else if("X".equals(citys.getLetter())){
					listX.add(citys.getName());
				}else if("Z".equals(citys.getLetter())){
					listZ.add(citys.getName());
				}
			}
			
			List list=new ArrayList();
			AppCitys appCitys=new AppCitys();
			appCitys.setLetter("A");
			appCitys.setName(listA);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("B");
			appCitys.setName(listB);
			list.add(appCitys);	
			appCitys=new AppCitys();
			appCitys.setLetter("C");
			appCitys.setName(listC);
			list.add(appCitys);	
			appCitys=new AppCitys();
			appCitys.setLetter("D");
			appCitys.setName(listD);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("E");
			appCitys.setName(listE);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("F");
			appCitys.setName(listF);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("G");
			appCitys.setName(listG);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("H");
			appCitys.setName(listH);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("I");
			appCitys.setName(listI);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("J");
			appCitys.setName(listJ);
			list.add(appCitys);
			
			appCitys=new AppCitys();
			appCitys.setLetter("K");
			appCitys.setName(listK);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("L");
			appCitys.setName(listL);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("M");
			appCitys.setName(listM);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("N");
			appCitys.setName(listN);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("O");
			appCitys.setName(listO);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("P");
			appCitys.setName(listP);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("Q");
			appCitys.setName(listQ);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("R");
			appCitys.setName(listR);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("S");
			appCitys.setName(listS);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("T");
			appCitys.setName(listT);
			list.add(appCitys);
			
			
			appCitys=new AppCitys();
			appCitys.setLetter("U");
			appCitys.setName(listU);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("V");
			appCitys.setName(listV);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("W");
			appCitys.setName(listW);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("Y");
			appCitys.setName(listY);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("X");
			appCitys.setName(listX);
			list.add(appCitys);
			appCitys=new AppCitys();
			appCitys.setLetter("Z");
			appCitys.setName(listZ);
			list.add(appCitys);
		
			
			
			return list;
		
		}
}
