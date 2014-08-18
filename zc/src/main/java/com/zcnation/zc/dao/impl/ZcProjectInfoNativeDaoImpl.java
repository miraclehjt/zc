package com.zcnation.zc.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.aspectj.apache.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.zcnation.zc.dao.ZcProjectInfoDao;
import com.zcnation.zc.dao.ZcProjectInfoNativeDao;
import com.zcnation.zc.domain.ZcProjectInfo;

@Repository("zcProjectInfoNativeDao")
public class ZcProjectInfoNativeDaoImpl implements ZcProjectInfoNativeDao{

	@Autowired private EntityManagerFactory entityManagerFactory;
	public List<ZcProjectInfo> findByUserCodeAndProShStatusAndProNameAndProShStatus(Integer userCode,
			String proName,String proShStatus) {
		// TODO Auto-generated method stub
		String sql="select zpi.PRO_CODE,zpi.PRO_NAME,zpi.PRO_SH_STATUS,zpi.PRO_TARGET,zpi.RESOURCE_CODE as zpircode,zri.RESOURCE_CODE as zrircode,zri.RESOURCE_NAME from zc_project_info zpi left JOIN zc_resource_info zri on zpi.RESOURCE_CODE=zri.RESOURCE_CODE where 1=1 ";
		
		if(userCode!=0){
		 sql=sql+"  and  zpi.USER_CODE="+userCode+"";
			
		}
		if (proShStatus != null && !proShStatus.equalsIgnoreCase("")&&!proShStatus.equalsIgnoreCase("-1")) {
			
			 sql=sql+"  and  zpi.PRO_SH_STATUS="+proShStatus+"";
		}
		
		if (proName != null && !proName.equalsIgnoreCase("")) {
			
			 sql=sql+"  and  zpi.PRO_NAME like '%"+proName+"%'";
		}
		
		EntityManager em=entityManagerFactory.createEntityManager();
		Query query=em.createNativeQuery(sql);
		List list=query.getResultList();
		for (Object object : list) {
			//这个object应该是个数组
			Object[] oj=(Object[])object;
			System.out.println("项目名称："+oj[0]+" 资源名称："+oj[1]);
		}
		return list;
	}
	@Override
	public List<ZcProjectInfo> findByProShStatus() {
		// TODO Auto-generated method stub
		String sql="select zpi.PRO_CODE,zpi.PRO_NAME,zpi.PRO_SH_STATUS,zpi.PRO_TARGET,zpi.RESOURCE_CODE as zpircode,zpi.pro_unit, zri.RESOURCE_CODE as zrircode,zri.RESOURCE_NAME,(select count(*) from  zc_project_like zpl where zpl.pro_code in(zpi.pro_code)) as likecount from zc_project_info zpi left JOIN zc_resource_info zri on zpi.RESOURCE_CODE=zri.RESOURCE_CODE where 1=1    order by   likecount desc limit 0, 6  ";
//String sql1="select zpi.PRO_CODE,zpi.PRO_NAME,zpi.PRO_SH_STATUS,zpi.PRO_TARGET,zpi.RESOURCE_CODE as zpircode,zri.RESOURCE_CODE as zrircode,zri.RESOURCE_NAME,(select count(*) from  zc_project_like zpl where zpl.pro_code in(zpi.pro_code)) as likecount from zc_project_info zpi left JOIN zc_resource_info zri on zpi.RESOURCE_CODE=zri.RESOURCE_CODE where 1=1  and zpi.PRO_SH_STATUS<>0   order by   likecount desc limit 0, 6  ";
	
	
		EntityManager em=entityManagerFactory.createEntityManager();
		Query query=em.createNativeQuery(sql);
		List list=query.getResultList();
		for (Object object : list) {
			//这个object应该是个数组
			Object[] oj=(Object[])object;
			System.out.println("项目名称："+oj[0]+" 资源名称："+oj[1]);
		}
		return list;
	}
	@Override
	public List<ZcProjectInfo> findByProShStatus(Integer currentPage) {
		// TODO Auto-generated method stub
		 int start = (currentPage - 1) * 4;  
			String sql="select zpi.PRO_CODE,zpi.PRO_NAME,zpi.PRO_SH_STATUS,zpi.PRO_TARGET,zpi.RESOURCE_CODE as zpircode,zpi.pro_unit, zri.RESOURCE_CODE as zrircode,zri.RESOURCE_NAME,(select count(*) from  zc_project_like zpl where zpl.pro_code in(zpi.pro_code)) as likecount from zc_project_info zpi left JOIN zc_resource_info zri on zpi.RESOURCE_CODE=zri.RESOURCE_CODE where 1=1    order by   likecount desc   ";
			EntityManager em=entityManagerFactory.createEntityManager();
		 Query query = em.createNativeQuery(sql); 
		 int total = query.getResultList().size();  
		 
		
		
			 query.setFirstResult(start);  
			 query.setMaxResults(4);  
			 List list=query.getResultList();
			         
				       

		 
		return list;
	}
	

}