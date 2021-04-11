package com.shop.cn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Autowired
    private RedisTemplate<String,Object> redisTemplateObject;
    @Test
    public void a(){
        String key1 = redisTemplate.opsForValue().get("key1");
        System.out.println("===============ok=============");
        System.out.println(key1);
    }

    @Test
    public void redisTest(){

    }

    @Test
    public void listTest(){
        List<Map<String,String>> list  = new ArrayList<>();
        Map<String,String> map =  new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        list.add(map);
        Map<String, String> test = list.get(0);
        String end = test.get(0);
        System.out.println(end);
    }


}
