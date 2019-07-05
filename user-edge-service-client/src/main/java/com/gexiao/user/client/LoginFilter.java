package com.gexiao.user.client;

import com.alibaba.fastjson.JSONObject;
import com.gexiao.user.dto.UserDTO;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: gexiao
 * @Date: 2019/6/24 15:39
 * @Description:
 */
public abstract class LoginFilter implements Filter {

    @Value("${server.user-edge.ip}")
    private String userEdgeIp;
    @Value("${server.user-edge.port}")
    private String userEdgePort;

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder()
            .maximumSize(10000).expireAfterWrite(3, TimeUnit.MINUTES).build();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getParameter("token");

        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), "token")) ;
                token = cookie.getName();
            }
        }

        UserDTO userDTO = null;
        if (StringUtils.isNotBlank(token)) {
            userDTO = cache.getIfPresent(token);
            if (userDTO == null) {
                userDTO = requestUserInfo(token);
                if (userDTO != null) {
                    cache.put(token, userDTO);
                }
            }
        } else {
            String url = new StringBuilder().append("http://").append(userEdgeIp).append(":")
                    .append(userEdgePort).append("/user/login").toString();
            response.sendRedirect(url);
            return;
        }

        login(request, response, userDTO);

        chain.doFilter(request, response);

    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private UserDTO requestUserInfo(String token) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String url = new StringBuilder().append("http://").append(userEdgeIp).append(":")
                .append(userEdgePort).append("/user/authentication").toString();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("token", token);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (response.getStatusLine().getStatusCode() != HttpStatus.OK.value())
                throw new RuntimeException("request user info failed StatusLine" + response.getStatusLine());

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                String entity = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + entity);

                UserDTO userDTO = JSONObject.parseObject(entity, UserDTO.class);
                return userDTO;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


}
