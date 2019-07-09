package com.gexiao.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gexiao.course.dto.CourseDTO;
import com.gexiao.course.service.ICourseService;
import com.gexiao.user.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Michael on 2017/11/4.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Reference
    private ICourseService courseService;

    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseList(HttpServletRequest request) {

        UserDTO user = (UserDTO) request.getAttribute("user");
        System.out.println("进到这里了" + user.toString());

        return courseService.courseList();
    }
}
