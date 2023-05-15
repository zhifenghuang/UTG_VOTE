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
import com.ruoyi.utg.domain.UserSpaceType;
import com.ruoyi.utg.service.IUserSpaceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 空间类型Controller
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/spaceType/spaceType")
public class UserSpaceTypeController extends BaseController
{
    private String prefix = "spaceType/spaceType";

    @Autowired
    private IUserSpaceTypeService userSpaceTypeService;

    @RequiresPermissions("spaceType:spaceType:view")
    @GetMapping()
    public String spaceType()
    {
        return prefix + "/spaceType";
    }

    /**
     * 查询空间类型列表
     */
    @RequiresPermissions("spaceType:spaceType:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserSpaceType userSpaceType)
    {
        startPage();
        List<UserSpaceType> list = userSpaceTypeService.selectUserSpaceTypeList(userSpaceType);
        return getDataTable(list);
    }

    /**
     * 导出空间类型列表
     */
    @RequiresPermissions("spaceType:spaceType:export")
    @Log(title = "空间类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserSpaceType userSpaceType)
    {
        List<UserSpaceType> list = userSpaceTypeService.selectUserSpaceTypeList(userSpaceType);
        ExcelUtil<UserSpaceType> util = new ExcelUtil<UserSpaceType>(UserSpaceType.class);
        return util.exportExcel(list, "spaceType");
    }

    /**
     * 新增空间类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存空间类型
     */
    @RequiresPermissions("spaceType:spaceType:add")
    @Log(title = "空间类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserSpaceType userSpaceType)
    {
        return toAjax(userSpaceTypeService.insertUserSpaceType(userSpaceType));
    }

    /**
     * 修改空间类型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserSpaceType userSpaceType = userSpaceTypeService.selectUserSpaceTypeById(id);
        mmap.put("userSpaceType", userSpaceType);
        return prefix + "/edit";
    }

    /**
     * 修改保存空间类型
     */
    @RequiresPermissions("spaceType:spaceType:edit")
    @Log(title = "空间类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserSpaceType userSpaceType)
    {
        return toAjax(userSpaceTypeService.updateUserSpaceType(userSpaceType));
    }

    /**
     * 删除空间类型
     */
    @RequiresPermissions("spaceType:spaceType:remove")
    @Log(title = "空间类型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userSpaceTypeService.deleteUserSpaceTypeByIds(ids));
    }
}
