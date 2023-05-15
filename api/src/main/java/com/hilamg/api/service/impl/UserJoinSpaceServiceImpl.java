package com.hilamg.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hilamg.api.dto.out.SpaceList;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserJoinSpace;
import com.hilamg.api.entity.UserSpace;
import com.hilamg.api.mapper.UserJoinSpaceMapper;
import com.hilamg.api.mapper.UserSpaceMapper;
import com.hilamg.api.service.IUserJoinSpaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hilamg.common.constant.ProConst;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户加入空间列表 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Service
public class UserJoinSpaceServiceImpl extends ServiceImpl<UserJoinSpaceMapper, UserJoinSpace> implements IUserJoinSpaceService {


    @Autowired
    UserJoinSpaceMapper userJoinSpaceMapper;

    @Autowired
    UserSpaceMapper userSpaceMapper;

    @Override
    public Result joinSpace(UserAddress userAddress, Integer spaceId) {
        UserSpace userSpace = userSpaceMapper.selectOne(new LambdaQueryWrapper<UserSpace>()
                .eq(UserSpace::getId, spaceId)
                .eq(UserSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
        if(null==userSpace){
            return ResultGenerator.genFailResult("not_has_space");
        }
      UserJoinSpace userJoinSpace= userJoinSpaceMapper.selectOne(new LambdaQueryWrapper<UserJoinSpace>()
                .eq(UserJoinSpace::getUserId,userAddress.getId())
              .eq(UserJoinSpace::getIsDelete,ProConst.DeleteEnum.NODELETE.getValue())
                 .eq(UserJoinSpace::getSpace,spaceId).last("limit 1"));
        if(null!=userJoinSpace){
            return ResultGenerator.genFailResult("has_join_space");
        }
        userJoinSpace=new UserJoinSpace();
        userJoinSpace.setUserId(userAddress.getId())
                     .setSpace(spaceId)
                     .setCreateDate(new Date());
        userJoinSpaceMapper.insert(userJoinSpace);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result exitSpace(UserAddress userAddress, Integer spaceId) {
        UserSpace userSpace = userSpaceMapper.selectOne(new LambdaQueryWrapper<UserSpace>()
                .eq(UserSpace::getId, spaceId)
                .eq(UserSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
        if(null==userSpace){
            return ResultGenerator.genFailResult("not_has_space");
        }
        if(userSpace.getOwnerId().equals(userAddress.getId())){
            return ResultGenerator.genFailResult("owner_not_exit");
        }
        UserJoinSpace userJoinSpace= userJoinSpaceMapper.selectOne(new LambdaQueryWrapper<UserJoinSpace>()
                .eq(UserJoinSpace::getUserId,userAddress.getId())
                .eq(UserJoinSpace::getIsDelete,ProConst.DeleteEnum.NODELETE.getValue())
                .eq(UserJoinSpace::getSpace,spaceId).last("limit 1"));
        if(null==userJoinSpace){
            return ResultGenerator.genFailResult("not_join_space");
        }
        userJoinSpace.setIsDelete(ProConst.DeleteEnum.DELETE.getValue()).setUpdateDate(new Date());
        userJoinSpaceMapper.updateById(userJoinSpace);
         return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result userSpaceList(UserAddress userAddress) {
       List<SpaceList> spaceList= userJoinSpaceMapper.getUserSpaceList(userAddress.getId());
        return ResultGenerator.genSuccessResult(spaceList);
    }
}
