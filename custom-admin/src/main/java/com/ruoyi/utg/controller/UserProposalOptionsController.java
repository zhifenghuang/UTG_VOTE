package com.ruoyi.utg.controller;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.utg.domain.UserProposalOptions;
import com.ruoyi.utg.service.IUserProposalOptionsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 提案投票选项Controller
 * 
 * @author hh
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/proposal/options")
public class UserProposalOptionsController extends BaseController
{
    private String prefix = "proposal/options";

    @Autowired
    private IUserProposalOptionsService userProposalOptionsService;

    @RequiresPermissions("proposal:options:view")
    @GetMapping()
    public String options()
    {
        return prefix + "/options";
    }

    /**
     * 查询提案投票选项列表
     */
    @RequiresPermissions("proposal:options:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserProposalOptions userProposalOptions)
    {
        startPage();
        String title=userProposalOptions.getTitle();
        if(StringUtils.isNotBlank(title)){
            userProposalOptions.setTitle(title.trim());
        }
        List<UserProposalOptions> list = userProposalOptionsService.selectUserProposalOptionsList(userProposalOptions);
        return getDataTable(list);
    }

    /**
     * 导出提案投票选项列表
     */
    @RequiresPermissions("proposal:options:export")
    @Log(title = "提案投票选项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserProposalOptions userProposalOptions)
    {
        List<UserProposalOptions> list = userProposalOptionsService.selectUserProposalOptionsList(userProposalOptions);
        ExcelUtil<UserProposalOptions> util = new ExcelUtil<UserProposalOptions>(UserProposalOptions.class);
        return util.exportExcel(list, "options");
    }

    /**
     * 新增提案投票选项
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存提案投票选项
     */
    @RequiresPermissions("proposal:options:add")
    @Log(title = "提案投票选项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserProposalOptions userProposalOptions)
    {
        return toAjax(userProposalOptionsService.insertUserProposalOptions(userProposalOptions));
    }

    /**
     * 修改提案投票选项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserProposalOptions userProposalOptions = userProposalOptionsService.selectUserProposalOptionsById(id);
        mmap.put("userProposalOptions", userProposalOptions);
        return prefix + "/edit";
    }

    /**
     * 修改保存提案投票选项
     */
    @RequiresPermissions("proposal:options:edit")
    @Log(title = "提案投票选项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserProposalOptions userProposalOptions)
    {
        return toAjax(userProposalOptionsService.updateUserProposalOptions(userProposalOptions));
    }

    /**
     * 删除提案投票选项
     */
    @RequiresPermissions("proposal:options:remove")
    @Log(title = "提案投票选项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userProposalOptionsService.deleteUserProposalOptionsByIds(ids));
    }
}
