package com.hilamg.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hilamg.api.entity.TbSysDict;
import com.hilamg.api.mapper.TbSysDictMapper;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-27
 */
@RestController
@RequestMapping("/api/sysDict")
public class TbSysDictController {


    @Autowired
    TbSysDictMapper sysDictMapper;

    @GetMapping("getUrl")
    @LogAnnotation(operateType = "系统相关", operateContent = "获取官网联系链接")
    public Result getToken() {
        List<TbSysDict> sysDictList = sysDictMapper.getValueByLike("url");
        return ResultGenerator.genSuccessResult(sysDictList);
    }

}
