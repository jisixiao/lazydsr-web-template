package com.lazydsr.lazydsrwebtemplate.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.lazydsr.lazydsrwebtemplate.base.STATICVALUE;
import com.lazydsr.lazydsrwebtemplate.entity.DataSourceInfo;
import com.lazydsr.lazydsrwebtemplate.repository.DataSourceInfoRepository;
import com.lazydsr.lazydsrwebtemplate.service.DataSourceInfoService;
import com.lazydsr.lazydsrwebtemplate.service.impl.DataSourceInfoServiceImpl;
import com.lazydsr.lazydsrwebtemplate.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MultiDatasourceConfiguration
 * PROJECT_NAME: lazydsr-web-template
 * PACKAGE_NAME: com.lazydsr.lazydsrwebtemplate.config.datasource
 * Created by Lazy on 2018/3/8 20:41
 * Version: 0.1
 * Info: 多数据源配置
 */

@Slf4j
public class DynamicDataSourceConfiguration extends AbstractRoutingDataSource {

    private static DynamicDataSourceConfiguration instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();


    public static synchronized DynamicDataSourceConfiguration getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DynamicDataSourceConfiguration();
                }
            }
        }
        return instance;
    }

    public void init() throws SQLException {
        setTargetDataSources(getDataSourceMap());
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {

        dataSourceMap.putAll(targetDataSources);
        super.setTargetDataSources(targetDataSources);
        //重要的方法,一定要加上不然会出现动态添加数据源的时候无法生效的情况
        super.afterPropertiesSet();

    }

    public Map<Object, Object> getDataSourceMap() throws SQLException {
        Map<Object, Object> result = new HashMap<Object, Object>();
        //result.put("dataSource", SpringContextUtil.getBean("dataSource"));
        DataSourceInfoRepository dataSourceInfoRepository = SpringContextUtil.getBean(DataSourceInfoRepository.class);
        List<DataSourceInfo> dataSourcesListInfo = dataSourceInfoRepository.findByStatus(STATICVALUE.ENABLE);
        for (DataSourceInfo ds : dataSourcesListInfo) {
            result.put(ds.getName(), buildDatasource(ds));
        }
        return result;
    }

    public DataSource buildDatasource(DataSourceInfo dataSourceInfo) throws SQLException {
        //DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setName(dataSourceInfo.getName());

        druidDataSource.setUrl(DataSourceUtil.getConnectUrl(dataSourceInfo.getDbtype(), dataSourceInfo.getAddress(), dataSourceInfo.getProt(), dataSourceInfo.getDbname(), "utf-8", false));
        druidDataSource.setUsername(dataSourceInfo.getUsername());
        druidDataSource.setPassword(dataSourceInfo.getPassword());
        druidDataSource.setDriverClassName(DatabaseTypeEnum.getDriverClassNameByDbType(dataSourceInfo.getDbtype()));
        druidDataSource.setInitialSize(dataSourceInfo.getMinActive());
        druidDataSource.setMaxActive(dataSourceInfo.getMaxActive());
        druidDataSource.setMinIdle(dataSourceInfo.getMinActive());


        druidDataSource.setMaxWait(60000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("SELECT 1 FROM dual");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        druidDataSource.setFilters("stat,wall");

        druidDataSource.init();
        return druidDataSource;
    }

    /**
     * Determine the current lookup key. This will typically be
     * implemented to check a thread-bound transaction context.
     * <p>Allows for arbitrary keys. The returned key needs
     * to match the stored lookup key type, as resolved by the
     * {@link #resolveSpecifiedLookupKey} method.
     */
    @Override
    protected Object determineCurrentLookupKey() {

        //return DataSourceContextHolder.getDataSourceType();
        return DataSourceContextHolder.getDataSourceType();
    }


}