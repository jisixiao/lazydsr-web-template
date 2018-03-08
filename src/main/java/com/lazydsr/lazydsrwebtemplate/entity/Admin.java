package com.lazydsr.lazydsrwebtemplate.entity;

import com.lazydsr.lazydsrwebtemplate.base.STATICVALUE;
import com.lazydsr.util.time.UtilDateTime;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Admin
 * PROJECT_NAME: lazydsr-web-template
 * PACKAGE_NAME: com.lazydsr.lazydsrwebtemplate.entity
 * Created by Lazy on 2018/3/5 22:48
 * Version: 0.1
 * Info: @TODO:...
 */
@Entity
@Data
public class Admin {
    @Id
    @GenericGenerator(name="Custom_UUID", strategy="com.lazydsr.commons.util.CustomIdentifierGenerator")
    @GeneratedValue(generator="Custom_UUID")
    @Column(length = 32)
    private int id;
    @Column
    private String name;

    private int status=STATICVALUE.ENABLE;


    private String creator;
    private String createDate= UtilDateTime.getCurrDateTime();
    private String modifier;
    private String modifyDate=UtilDateTime.getCurrDateTime();
    private int dataStatus= STATICVALUE.ENABLE;
}
