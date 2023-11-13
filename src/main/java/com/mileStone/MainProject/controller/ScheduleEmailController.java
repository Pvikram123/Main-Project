package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.service.ScheduleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Schedule")
public class ScheduleEmailController {
    @Autowired
    ScheduleEmailService scheduleEmailService;
    @PostMapping
    public Object demo1(){
        return scheduleEmailService.demo();
    }
    @DeleteMapping
    public String demo2(){
        return scheduleEmailService.del();
    }
}
