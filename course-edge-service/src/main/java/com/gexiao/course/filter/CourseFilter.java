package com.gexiao.course.filter;

import com.gexiao.user.client.LoginFilter;
import com.gexiao.user.dto.UserDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 2017/11/4.
 */
@Component
public class CourseFilter extends LoginFilter {

//    @Value("${user.edge.service.addr}")
//    private String userEdgeServiceAddr;

//    @Override
//    protected String userEdgeServiceAddr() {
//        return userEdgeServiceAddr;
//    }

    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {

        request.setAttribute("user", userDTO);
    }
}
