package com.ruoyi.utg.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.utg.domain.UserJoinSpace;
import com.ruoyi.utg.service.IUserJoinSpaceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户加入空间列Controller
 * 
 * @author hh
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/space/joinSpace")
public class UserJoinSpaceController extends BaseController
{
    private String prefix = "space/joinSpace";

    @Autowired
    private IUserJoinSpaceService userJoinSpaceService;

    @RequiresPermissions("space:joinSpace:view")
    @GetMapping()
    public String joinSpace()
    {
        return prefix + "/joinSpace";
    }

    /**
     * 查询用户加入空间列列表
     */
    @RequiresPermissions("space:joinSpace:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserJoinSpace userJoinSpace)
    {
        startPage();
        List<UserJoinSpace> list = userJoinSpaceService.selectUserJoinSpaceList(userJoinSpace);
        return getDataTable(list);
    }

    /**
     * 导出用户加入空间列列表
     */
    @RequiresPermissions("space:joinSpace:export")
    @Log(title = "用户加入空间列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserJoinSpace userJoinSpace)
    {
        List<UserJoinSpace> list = userJoinSpaceService.selectUserJoinSpaceList(userJoinSpace);
        ExcelUtil<UserJoinSpace> util = new ExcelUtil<UserJoinSpace>(UserJoinSpace.class);
        return util.exportExcel(list, "joinSpace");
    }

    /**
     * 新增用户加入空间列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户加入空间列
     */
    @RequiresPermissions("space:joinSpace:add")
    @Log(title = "用户加入空间列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserJoinSpace userJoinSpace)
    {
        return toAjax(userJoinSpaceService.insertUserJoinSpace(userJoinSpace));
    }

    /**
     * 修改用户加入空间列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserJoinSpace userJoinSpace = userJoinSpaceService.selectUserJoinSpaceById(id);
        mmap.put("userJoinSpace", userJoinSpace);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户加入空间列
     */
    @RequiresPermissions("space:joinSpace:edit")
    @Log(title = "用户加入空间列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserJoinSpace userJoinSpace)
    {
        return toAjax(userJoinSpaceService.updateUserJoinSpace(userJoinSpace));
    }

    /**
     * 删除用户加入空间列
     */
    @RequiresPermissions("space:joinSpace:remove")
    @Log(title = "用户加入空间列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userJoinSpaceService.deleteUserJoinSpaceByIds(ids));
    }
}
