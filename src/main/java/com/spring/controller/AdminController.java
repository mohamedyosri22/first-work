package com.spring.controller;

import com.spring.model.User;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//localhost:9090
@Controller
@RequestMapping("/Admin")
//localhost:9090/Admin
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    //http://localhost:9090/api/Getusers
    @GetMapping("/getUsers")
    public String getAdmin(){

        return "admin";
    }

    @GetMapping("/get")
    public String get(){

        return "index";
    }



}
