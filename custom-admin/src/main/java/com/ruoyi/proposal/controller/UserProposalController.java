package com.ruoyi.proposal.controller;

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
import com.ruoyi.proposal.domain.UserProposal;
import com.ruoyi.proposal.service.IUserProposalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 提案列Controller
 * 
 * @author hh
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/proposal/proposal")
public class UserProposalController extends BaseController
{
    private String prefix = "proposal/proposal";

    @Autowired
    private IUserProposalService userProposalService;

    @RequiresPermissions("proposal:proposal:view")
    @GetMapping()
    public String proposal()
    {
        return prefix + "/proposal";
    }

    /**
     * 查询提案列列表
     */
    @RequiresPermissions("proposal:proposal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserProposal userProposal)
    {
        startPage();
        List<UserProposal> list = userProposalService.selectUserProposalList(userProposal);
        return getDataTable(list);
    }

    /**
     * 导出提案列列表
     */
    @RequiresPermissions("proposal:proposal:export")
    @Log(title = "提案列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserProposal userProposal)
    {
        List<UserProposal> list = userProposalService.selectUserProposalList(userProposal);
        ExcelUtil<UserProposal> util = new ExcelUtil<UserProposal>(UserProposal.class);
        return util.exportExcel(list, "proposal");
    }

    /**
     * 新增提案列
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存提案列
     */
    @RequiresPermissions("proposal:proposal:add")
    @Log(title = "提案列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserProposal userProposal)
    {
        return toAjax(userProposalService.insertUserProposal(userProposal));
    }

    /**
     * 修改提案列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserProposal userProposal = userProposalService.selectUserProposalById(id);
        mmap.put("userProposal", userProposal);
        return prefix + "/edit";
    }

    /**
     * 修改保存提案列
     */
    @RequiresPermissions("proposal:proposal:edit")
    @Log(title = "提案列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserProposal userProposal)
    {
        return toAjax(userProposalService.updateUserProposal(userProposal));
    }

    /**
     * 删除提案列
     */
    @RequiresPermissions("proposal:proposal:remove")
    @Log(title = "提案列", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userProposalService.deleteUserProposalByIds(ids));
    }
}
