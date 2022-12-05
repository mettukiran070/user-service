package com.example.user.controller;

import com.example.user.VO.ResponseTemplateVo;
import com.example.user.entity.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/")
    public ResponseEntity<User> save(@RequestBody User user) {
        log.info("inside UserController :: save method");
        return new ResponseEntity<>(this.service.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        log.info("inside UserController :: getAll method");
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplateVo> getById(@PathVariable("id") Long id) {
        log.info("inside UserController :: getById method");
        return new ResponseEntity<>(this.service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        log.info("inside UserController :: update method");
        //this.service.getById(id).orElseThrow(() -> new UserNotFoundException());
        return new ResponseEntity<>(this.service.updateById(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        log.info("inside UserController :: deleteById method");
       // this.service.getById(id).orElseThrow(() -> new UserNotFoundException());
        this.service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
