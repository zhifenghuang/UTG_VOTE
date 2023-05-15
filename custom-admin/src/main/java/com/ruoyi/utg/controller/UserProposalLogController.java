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
import com.ruoyi.utg.domain.UserProposalLog;
import com.ruoyi.utg.service.IUserProposalLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投票记录Controller
 * 
 * @author hh
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/proposal/log")
public class UserProposalLogController extends BaseController
{
    private String prefix = "proposal/log";

    @Autowired
    private IUserProposalLogService userProposalLogService;

    @RequiresPermissions("proposal:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询投票记录列表
     */
    @RequiresPermissions("proposal:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserProposalLog userProposalLog)
    {
        startPage();
        List<UserProposalLog> list = userProposalLogService.selectUserProposalLogList(userProposalLog);
        return getDataTable(list);
    }

    /**
     * 导出投票记录列表
     */
    @RequiresPermissions("proposal:log:export")
    @Log(title = "投票记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserProposalLog userProposalLog)
    {
        List<UserProposalLog> list = userProposalLogService.selectUserProposalLogList(userProposalLog);
        ExcelUtil<UserProposalLog> util = new ExcelUtil<UserProposalLog>(UserProposalLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增投票记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存投票记录
     */
    @RequiresPermissions("proposal:log:add")
    @Log(title = "投票记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserProposalLog userProposalLog)
    {
        return toAjax(userProposalLogService.insertUserProposalLog(userProposalLog));
    }

    /**
     * 修改投票记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserProposalLog userProposalLog = userProposalLogService.selectUserProposalLogById(id);
        mmap.put("userProposalLog", userProposalLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存投票记录
     */
    @RequiresPermissions("proposal:log:edit")
    @Log(title = "投票记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserProposalLog userProposalLog)
    {
        return toAjax(userProposalLogService.updateUserProposalLog(userProposalLog));
    }

    /**
     * 删除投票记录
     */
    @RequiresPermissions("proposal:log:remove")
    @Log(title = "投票记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userProposalLogService.deleteUserProposalLogByIds(ids));
    }
}
