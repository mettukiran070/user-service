package com.example.user.service;

import com.example.user.VO.Department;
import com.example.user.VO.ResponseTemplateVo;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user) {
        log.info("inside UserService :: save method.");
        return this.repository.save(user);
    }

    public List<User> getAll(){
        log.info("inside UserService :: getAll method.");
        return this.repository.findAll();
    }

    public ResponseTemplateVo getById(Long id) {
        log.info("inside UserService :: getById method.");
        ResponseTemplateVo response = new ResponseTemplateVo();
        User user = this.repository.findById(id).get();
        Department department = restTemplate.getForObject("http://localhost:9001/api/departments/"+ user.getDepartmentId(), Department.class);
        response.setUser(user);
        response.setDepartment(department);
        return response;
    }

    public User updateById(User user) {
        log.info("inside UserService :: updateById method.");
        return this.repository.save(user);
    }

    public void deleteById(Long id) {
        log.info("inside UserService :: deleteById method.");
        this.repository.deleteById(id);
    }

}
