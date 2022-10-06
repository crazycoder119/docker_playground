package com.eagleai.user.service;

import com.eagleai.user.VO.Department;
import com.eagleai.user.VO.ResponseTemplateVO;
import com.eagleai.user.entity.User;
import com.eagleai.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    public User saveUser(User user) {
        log.info("Inside UserService saveUser method");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside UserService getUserWithDepartment method");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
        vo.setDepartment(department);
        vo.setUser(user);
        return vo;
    }
}
