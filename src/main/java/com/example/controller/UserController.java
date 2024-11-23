package com.example.controller;

import com.example.common.Result;
import com.example.entity.Params;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    public Result save(@RequestBody User user){
        if (user.getId() == null) {
            userService.add(user);
        }else{
            userService.update(user);
        }
        return Result.success();
    }

        @GetMapping
    public Result findAll(){
            List<User> list = userService.findAll();
            return Result.success(list);
        }
        @GetMapping("/search")
    public Result find(Params params){
            PageInfo<User> info = userService.find(params);
            return Result.success(info);
        }
    @GetMapping("/isshoucang")
    public Result find1(Params params){
        PageInfo<User> info = userService.find1(params);
        return Result.success(info);
    }



    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }

    }

