package com.lazydsr.lazydsrwebtemplate.service.impl;

import com.lazydsr.lazydsrwebtemplate.entity.SystemInfo;
import com.lazydsr.lazydsrwebtemplate.repository.SystemInfoRepository;
import com.lazydsr.lazydsrwebtemplate.service.SystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SystemInfoServiceImpl
 * PROJECT_NAME: lazydsr-web-template
 * PACKAGE_NAME: com.lazydsr.lazydsrwebtemplate.service.impl
 * Created by Lazy on 2018/3/15 0:06
 * Version: 0.1
 * Info: @TODO:...
 */
@Service
public class SystemInfoServiceImpl implements SystemInfoService {

    @Override
    public SystemInfo add(SystemInfo systemInfo) {
        return null;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public SystemInfo update(SystemInfo systemInfo) {
        return null;
    }

    @Override
    public SystemInfo findById(String id) {
        return null;
    }

    @Override
    public SystemInfo findLastRecord() {
        return null;
    }
}
