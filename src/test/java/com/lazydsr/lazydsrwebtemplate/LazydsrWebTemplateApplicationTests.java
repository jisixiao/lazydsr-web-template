package com.lazydsr.lazydsrwebtemplate;

import com.lazydsr.lazydsrwebtemplate.dao.UserDao;
import com.lazydsr.lazydsrwebtemplate.entity.DatasourceInfo;
import com.lazydsr.lazydsrwebtemplate.entity.User;
import com.lazydsr.lazydsrwebtemplate.repository.DatasourceInfoRepository;
import com.lazydsr.lazydsrwebtemplate.repository.UserRepository;
import com.lazydsr.util.time.UtilDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LazydsrWebTemplateApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DatasourceInfoRepository datasourceInfoRepository;

    @Test
    public void contextLoads() {
        User user = userRepository.save(new User("aa"));
        System.out.println(user);

        userRepository.findAll().stream().forEach(u -> System.out.println(u));

    }

    @Test
    public void contextLoads1() {
        User user = userRepository.save(new User("aa"));
        System.out.println(user);

        //userRepository.findByNameLike("aa").stream().forEach(u -> System.out.println(u));

    }

    @Test
    public void contextLoads2() {
        User user = userRepository.save(new User("aa"));
        System.out.println(user);

        userRepository.findByNameLike("%a%").stream().forEach(u -> System.out.println(u));

    }

    @Test
    public void contextLoads3() {
        User user = userRepository.save(new User("aa"));
        System.out.println(user);

        //userRepository.findByNameLikeCus("aa").stream().forEach(u-> System.out.println(u));
        //userRepository.findByNameLikeCus1("aa").stream().forEach(u-> System.out.println(u));
        //userRepository.findByNameLikeCus2("aa").stream().forEach(u-> System.out.println(u));
        //System.out.println("-------------");
        userDao.findbyNameJdbc("aa").stream().forEach(u -> System.out.println(u));

    }
    @Test
    public void contextLoads4() {
        DatasourceInfo datasourceInfo=new DatasourceInfo();
        datasourceInfo.setName(UtilDateTime.getCurrSecond()+"");
        DatasourceInfo info = datasourceInfoRepository.save(datasourceInfo);
        System.out.println(info);

    }

}
