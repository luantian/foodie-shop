package com.imooc.controller;


import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车", tags = {"购物车相关接口"})
@RequestMapping("/shopcart")
@RestController
public class ShopcartController {

    @ApiOperation(value="添加商品到购物车", notes = "添加商品到购物车", httpMethod="POST")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("");
        }

        System.out.println(shopcartBO);

        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return JSONResult.ok();
    }
}
