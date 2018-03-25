package com.lazydsr.lazydsrwebtemplate.service;

import com.lazydsr.lazydsrwebtemplate.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * MenuService
 * PROJECT_NAME: lazydsr-web-template
 * PACKAGE_NAME: com.lazydsr.lazydsrwebtemplate.service
 * Created by Lazy on 2018/3/19 15:58
 * Version: 0.1
 * Info: @TODO:...
 */
public interface MenuService {
    Menu add(Menu menu);

    int delete(String id);

    Menu update(Menu menu);

    Menu findById(String id);

    List<Menu> findAll();

    List<Menu> findPage(int page, int limit);
}
