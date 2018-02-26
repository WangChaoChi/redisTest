package com.wcc.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wangcc
 * @decription:
 * @date 2018/2/2 10:10
 */
public class JedisDemo1 {

    @Test
    /*
    * 单实例测试
    * */
    public void demo1(){

//      1.设置ip地址或端口号
        Jedis jedis = new Jedis("192.168.1.113",6379);
//        2.保存数据
        jedis.set("name", "wcc");
//        3.获取数据
        String value = jedis.get("name");
        System.out.println(value);
    }

    @Test
    /*
    * 使用连接池的方式
    * */
    public void demo2(){
        //创建连接池配置对象
        JedisPoolConfig jpc = new JedisPoolConfig();
        //设置最大连接数
        jpc.setMaxTotal(30);
        //设置最大空闲连接数
        jpc.setMaxIdle(10);

        //获取连接池
        JedisPool jedisPool = new JedisPool(jpc, "192.168.1.113", 6379);

        //获得核心对象
        Jedis jedis = null;
        try {
            //通过连接池获得连接
            jedis = jedisPool.getResource();
            jedis.set("sex", "男");
            System.out.println(jedis.get("sex"));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis == null) {
                jedis.close();
            }
            if (jedisPool == null) {
                jedisPool.close();
            }
        }




    }
}
