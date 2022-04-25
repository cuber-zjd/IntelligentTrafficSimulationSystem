package com.cuber;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.cuber.service.IUserService;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class IntelligentTrafficSimulationSystemApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/graduationproject", "root", "zhang")
                .globalConfig(builder -> {
                    builder.author("cuber") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.cuber") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("detect_setting"); // 设置需要生成的表名
                })
                .execute();    }

    @Autowired
    private IUserService userService;

    @Test
    void testGetAll(){
        System.out.println(userService.list());
    }

    private long time = 1000*60*60*24;
    private String signature="admin";

    @Test
    void testJwt(){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                .claim("username","tom")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
//                signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);


    }

    @Test
    void parse(){
        String token="eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiIxMjMiLCJzdWIiOiJ1c2VyLWxvZ2luIiwiZXhwIjoxNjQ2MDQxNzcyLCJqdGkiOiIzNWFhYjExYi1lYjJlLTRmMWItOTEwZi1jNGZjYzQ4N2RmNzYifQ.dfr_6g9qXu-iITWvx4JRJ_dszc-WQhGgf-Jamr5uaWA";
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey("test").parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        if (body.getExpiration().after(new Date())){
            System.out.println("token");
        }
        System.out.println(body.get("username"));
        System.out.println(body.getExpiration());
    }
    @Test
    void testRuntime(){
        try {
            String[] args = new String[]{"conda activate pytorch"};
            // 执行py文件
            Process proc = Runtime.getRuntime().exec(args);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
