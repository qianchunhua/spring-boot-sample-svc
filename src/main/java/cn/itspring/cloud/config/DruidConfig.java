package cn.itspring.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: DruidConfig
 * Description:Druid配置类
 *
 * @author Spring
 * @email coderspring@163.com
 * @date 2019/8/29 22:06
 * @since 1.0.0
 */

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }


    // 配置druid监控
    // 1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean StatViewServlet() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(
                new StatViewServlet(), "/druid");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        initParameters.put("allow", "");// 默认允许所有访问
//        initParameters.put("deny","");// 拒绝xxx访问
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }



    // 2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean WebStatFilter() {
        FilterRegistrationBean<Filter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new WebStatFilter());
        Map<String, String> stringParm = new HashMap<>();
        stringParm.put("exclusions","*.js,*.css,*.druid/*");
        filterBean.setInitParameters(stringParm);
        filterBean.setUrlPatterns(Arrays.asList("/**"));
        return filterBean;

    }
}






