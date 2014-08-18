package com.zcnation.zc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcnation.zc.dao.ZcProjectInfoDao;
import com.zcnation.zc.dao.ZcProjectInfoNativeDao;
import com.zcnation.zc.domain.ZcProjectInfo;
import com.zcnation.zc.service.ZcProjectInfoNativeService;

@Service(value = "zcProjectInfoNativeService")
public class ZcProjectInfoNativeServiceImpl implements ZcProjectInfoNativeService {
	@Autowired private ZcProjectInfoNativeDao zcProjectInfoNativeDao;
	@Override
	public List<ZcProjectInfo> queryByUserCodeAndProShStatusAndProNameAndProShStatus(Integer userCode,
			String proName,String proShStatus) {
		// TODO Auto-generated method stub
	return	zcProjectInfoNativeDao.findByUserCodeAndProShStatusAndProNameAndProShStatus(userCode, proName,proShStatus);
	}
	@Override
	public List<ZcProjectInfo> queryByProShStatus() {
		// TODO Auto-generated method stub
		return zcProjectInfoNativeDao.findByProShStatus();
	}
	@Override
	public List<ZcProjectInfo> queryByProShStatus(Integer currentPage) {
		// TODO Auto-generated method stub
		return zcProjectInfoNativeDao.findByProShStatus(currentPage);
	}

}