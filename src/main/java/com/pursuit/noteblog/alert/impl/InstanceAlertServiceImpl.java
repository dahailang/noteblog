package com.pursuit.noteblog.alert.impl;

import java.util.List;
import com.pursuit.noteblog.alert.InstanceAlertService;
import com.pursuit.noteblog.dao.InstanceFaultDao;
import com.pursuit.noteblog.entity.InstanceFault;

/**
 * 实例报警
 */
public class InstanceAlertServiceImpl extends BaseAlertService implements InstanceAlertService {
    
    private InstanceFaultDao instanceFaultDao;

    @Override
    public List<InstanceFault> getListByInstId(int instId) {
        return instanceFaultDao.getListByInstId(instId);
    }

    public void setInstanceFaultDao(InstanceFaultDao instanceFaultDao) {
        this.instanceFaultDao = instanceFaultDao;
    }
}
