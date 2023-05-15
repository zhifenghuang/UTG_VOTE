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
import com.ruoyi.utg.domain.UserAddress;
import com.ruoyi.utg.service.IUserAddressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户地址信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Controller
@RequestMapping("/userAddress/address")
public class UserAddressController extends BaseController
{
    private String prefix = "userAddress/address";

    @Autowired
    private IUserAddressService userAddressService;

    @RequiresPermissions("userAddress:address:view")
    @GetMapping()
    public String address()
    {
        return prefix + "/address";
    }

    /**
     * 查询用户地址信息列表
     */
    @RequiresPermissions("userAddress:address:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserAddress userAddress)
    {
        startPage();
        List<UserAddress> list = userAddressService.selectUserAddressList(userAddress);
        return getDataTable(list);
    }

    /**
     * 导出用户地址信息列表
     */
    @RequiresPermissions("userAddress:address:export")
    @Log(title = "用户地址信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserAddress userAddress)
    {
        List<UserAddress> list = userAddressService.selectUserAddressList(userAddress);
        ExcelUtil<UserAddress> util = new ExcelUtil<UserAddress>(UserAddress.class);
        return util.exportExcel(list, "address");
    }

    /**
     * 新增用户地址信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户地址信息
     */
    @RequiresPermissions("userAddress:address:add")
    @Log(title = "用户地址信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserAddress userAddress)
    {
        return toAjax(userAddressService.insertUserAddress(userAddress));
    }

    /**
     * 修改用户地址信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserAddress userAddress = userAddressService.selectUserAddressById(id);
        mmap.put("userAddress", userAddress);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户地址信息
     */
    @RequiresPermissions("userAddress:address:edit")
    @Log(title = "用户地址信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserAddress userAddress)
    {
        return toAjax(userAddressService.updateUserAddress(userAddress));
    }

    /**
     * 删除用户地址信息
     */
    @RequiresPermissions("userAddress:address:remove")
    @Log(title = "用户地址信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userAddressService.deleteUserAddressByIds(ids));
    }
}
