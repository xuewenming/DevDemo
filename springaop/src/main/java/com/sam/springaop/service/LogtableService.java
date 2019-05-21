package com.sam.springaop.service;

import com.sam.springaop.pojo.Logtable;

/**
 * 日志实体类
 * @author Sam
 * @date 2019/5/16
 * @time 15:00
 */
public interface LogtableService {

    /**
     * 增加日志接口
     * @param logtable
     * @return
     */
    boolean addLog(Logtable logtable);
}
