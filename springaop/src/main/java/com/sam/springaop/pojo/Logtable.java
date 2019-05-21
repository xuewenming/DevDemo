package com.sam.springaop.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Logtable {
    private Integer id;

    private String operateor;

    private String operatetype;

    private Date operatedate;

    private String operateresult;

    private String remark;


}