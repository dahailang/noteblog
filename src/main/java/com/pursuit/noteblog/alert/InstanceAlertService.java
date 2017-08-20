package com.pursuit.noteblog.alert;
import java.util.List;

import com.pursuit.noteblog.entity.InstanceFault;

/**
 * 实例报警检测
 * @author leifu
 * @Date 2014年12月16日
 * @Time 下午1:56:35
 */
public interface InstanceAlertService {
    
    /**
     * 实例故障列表
     *
     * @param instId
     * @return
     */
    List<InstanceFault> getListByInstId(int instId);

}
