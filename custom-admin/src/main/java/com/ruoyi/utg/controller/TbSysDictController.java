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
import com.ruoyi.utg.domain.TbSysDict;
import com.ruoyi.utg.service.ITbSysDictService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 固定参数配置Controller
 * 
 * @author hh
 * @date 2022-06-24
 */
@Controller
@RequestMapping("/dict/dict")
public class TbSysDictController extends BaseController
{
    private String prefix = "dict/dict";

    @Autowired
    private ITbSysDictService tbSysDictService;

    @RequiresPermissions("dict:dict:view")
    @GetMapping()
    public String dict()
    {
        return prefix + "/dict";
    }

    /**
     * 查询固定参数配置列表
     */
    @RequiresPermissions("dict:dict:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TbSysDict tbSysDict)
    {
        startPage();
        List<TbSysDict> list = tbSysDictService.selectTbSysDictList(tbSysDict);
        return getDataTable(list);
    }

    /**
     * 导出固定参数配置列表
     */
    @RequiresPermissions("dict:dict:export")
    @Log(title = "固定参数配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbSysDict tbSysDict)
    {
        List<TbSysDict> list = tbSysDictService.selectTbSysDictList(tbSysDict);
        ExcelUtil<TbSysDict> util = new ExcelUtil<TbSysDict>(TbSysDict.class);
        return util.exportExcel(list, "dict");
    }

    /**
     * 新增固定参数配置
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存固定参数配置
     */
    @RequiresPermissions("dict:dict:add")
    @Log(title = "固定参数配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TbSysDict tbSysDict)
    {
        return toAjax(tbSysDictService.insertTbSysDict(tbSysDict));
    }

    /**
     * 修改固定参数配置
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TbSysDict tbSysDict = tbSysDictService.selectTbSysDictById(id);
        mmap.put("tbSysDict", tbSysDict);
        return prefix + "/edit";
    }

    /**
     * 修改保存固定参数配置
     */
    @RequiresPermissions("dict:dict:edit")
    @Log(title = "固定参数配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TbSysDict tbSysDict)
    {
        return toAjax(tbSysDictService.updateTbSysDict(tbSysDict));
    }

    /**
     * 删除固定参数配置
     */
    @RequiresPermissions("dict:dict:remove")
    @Log(title = "固定参数配置", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tbSysDictService.deleteTbSysDictByIds(ids));
    }
}
