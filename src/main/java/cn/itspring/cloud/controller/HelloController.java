package cn.itspring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Class Name: HelloController
 * Description:
 *
 * @author Spring
 * @email coderspring@163.com
 * @date 2019/8/29 21:51
 * @since 1.0.0
 */

@Controller
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/queryuserList")
    public Map<String, Object> map() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "SELECT * FROM bpm_orguserlist");
        return list.get(0);
    }
}
