package com.example.demo01s.server;

import com.example.demo01s.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

// Rest模式
@RestController
@RequestMapping
public class DemoServer {

    // 静态模拟数据
    private static List<User> userList = new ArrayList<>(); // 创建用户类组

    static {
        User user1 = new User(); // 创建用户类
        user1.setName("用户A"); // 设置属性
        user1.setAge("18岁");
        userList.add(user1); // 添加进组

        User user2 = new User();
        user2.setName("用户B");
        user2.setAge("20岁");
        userList.add(user2);
    }

    // 客户端使用get链接获取json数据
    @GetMapping("/get") // 链接:ip:port/get
    public String get() {
        JSONArray result = JSONArray.fromObject(userList); // 使用JSONArray包处理ArrayList数据
        return result.toString(); // 返回String数据
    }

    // 客户端使用post链接提交URL数据给服务端
    @PostMapping(value = "/posturl")// 链接:ip:port/posturl
    public void posturl(HttpServletRequest httpServletRequest) { // 使用HttpServletRequest包处理获取的数据
        User user = new User(); // 新建用户类
        user.setName(httpServletRequest.getParameter("name")); // 使用HttpServletRequest包的getParameter方法提取数据，将提取数据赋值给用户类
        user.setAge(httpServletRequest.getParameter("age"));
        System.out.println("收到数据:" + JSONArray.fromObject(user).toString()); // // 使用JSONArray包处理ArrayList数据，转为string打印
        //return user;
    }

    // 客户端使用post链接提交JSON数据给服务端
    @PostMapping(value = "/postjson")// 链接:ip:port/postjson
    public void postjson(@RequestBody JSONObject params) { // 使用JSONObject包处理获取的数据
        System.out.println("收到数据:" + params.toString()); //打印获取的数据
        //return user;
    }
}