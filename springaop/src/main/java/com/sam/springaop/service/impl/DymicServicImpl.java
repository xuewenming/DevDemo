package com.sam.springaop.service.impl;

import com.sam.springaop.service.DymicService;
import org.springframework.stereotype.Service;

/**
 * @author Sam
 * @date 2019/5/20
 * @time 12:29
 */
@Service
public class DymicServicImpl implements DymicService {
    @Override
    public void sayHello() {
        System.out.println("----------------接口sayHello-------------------------");
    }
}
