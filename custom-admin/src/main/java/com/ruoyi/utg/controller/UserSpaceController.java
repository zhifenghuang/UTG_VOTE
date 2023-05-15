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
import com.ruoyi.utg.domain.UserSpace;
import com.ruoyi.utg.service.IUserSpaceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户空间Controller
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/userSpace/space")
public class UserSpaceController extends BaseController
{
    private String prefix = "userSpace/space";

    @Autowired
    private IUserSpaceService userSpaceService;

    @RequiresPermissions("userSpace:space:view")
    @GetMapping()
    public String space()
    {
        return prefix + "/space";
    }

    /**
     * 查询用户空间列表
     */
    @RequiresPermissions("userSpace:space:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserSpace userSpace)
    {
        startPage();
        List<UserSpace> list = userSpaceService.selectUserSpaceList(userSpace);
        return getDataTable(list);
    }

    /**
     * 导出用户空间列表
     */
    @RequiresPermissions("userSpace:space:export")
    @Log(title = "用户空间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserSpace userSpace)
    {
        List<UserSpace> list = userSpaceService.selectUserSpaceList(userSpace);
        ExcelUtil<UserSpace> util = new ExcelUtil<UserSpace>(UserSpace.class);
        return util.exportExcel(list, "space");
    }

    /**
     * 新增用户空间
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户空间
     */
    @RequiresPermissions("userSpace:space:add")
    @Log(title = "用户空间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserSpace userSpace)
    {
        return toAjax(userSpaceService.insertUserSpace(userSpace));
    }

    /**
     * 修改用户空间
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserSpace userSpace = userSpaceService.selectUserSpaceById(id);
        mmap.put("userSpace", userSpace);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户空间
     */
    @RequiresPermissions("userSpace:space:edit")
    @Log(title = "用户空间", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserSpace userSpace)
    {
        return toAjax(userSpaceService.updateUserSpace(userSpace));
    }

    /**
     * 删除用户空间
     */
    @RequiresPermissions("userSpace:space:remove")
    @Log(title = "用户空间", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userSpaceService.deleteUserSpaceByIds(ids));
    }
}
