package com.sam.springaop.service.impl;

import com.sam.springaop.annotation.LogAnn;
import com.sam.springaop.mapper.LogtableMapper;
import com.sam.springaop.pojo.Logtable;
import com.sam.springaop.service.LogtableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sam
 * @date 2019/5/16
 * @time 15:16
 */
@Service
public class LogServiceImpl implements LogtableService {
    @Autowired
    private LogtableMapper logtableMapper;

    @LogAnn(operateType = "新增日志")
    @Override
    public boolean addLog(Logtable logtable) {
        int insert = logtableMapper.insert(logtable);
        if( insert != 0 ) {
            return true;
        }
        return false;
    }
}
