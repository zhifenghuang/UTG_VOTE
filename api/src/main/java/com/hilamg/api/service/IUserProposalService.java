package com.hilamg.api.service;

import com.hilamg.api.dto.in.AddProposal;
import com.hilamg.api.dto.in.ProposalOptions;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserProposal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 提案列表 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
public interface IUserProposalService extends IService<UserProposal> {

    Result addProposal(UserAddress userAddress, AddProposal addProposal, List<ProposalOptions> proposalOptions);

    Result getProposalList(UserAddress userAddress,String status,Integer spaceId);

    Result getProposalInfo(UserAddress userAddress,Integer id);

    Result getTimeLineList(@LoginUserAnnotation UserAddress userAddress,String type,String status);

    Result getUserProposalLog(@LoginUserAnnotation UserAddress userAddress);

    Result updateProposalOnLineStatus();

    Result addProposalLogs() throws ExecutionException, InterruptedException;

    Result checkProposalStatus();

}
