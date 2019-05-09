package com.framework.loippi.service.impl;

import com.framework.loippi.api.utils.ApiUtils;
import com.framework.loippi.dao.FriendsDao;
//import com.framework.loippi.dto.user.FriendsDto;
import com.framework.loippi.entity.Friends;
import com.framework.loippi.entity.TUserIdx;
import com.framework.loippi.service.FileService;
import com.framework.loippi.service.FriendsService;
import com.framework.loippi.service.TUserIdxService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * SERVICE - Friends(user)
 *
 * @author huangqi
 * @version 2.0
 */
@Service
public class FriendsServiceImpl extends GenericServiceImpl<Friends, Long>
        implements FriendsService {

    @Autowired
    private FriendsDao friendsDao;

    @Autowired
    private TUserIdxService tUserIdxService;

    @Autowired
    public void setGenericDao() {
        super.setGenericDao(friendsDao);
    }

//    /**
//     * 申请加好友
//     */
//    @Override
//    public boolean addFriends(Long uid, Long destUid) {
//        if (uid.intValue() == destUid.intValue()) {
//            return false;
//        }
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", uid);
//        params.put("destUid", destUid);
//        List<Friends> friends = this.findList(params);
//        Friends friend = null;
//        if (CollectionUtils.isEmpty(friends)) {
//            friend = new Friends();
//            friend.setUid(uid);
//            friend.setDestUid(destUid);
//            friend.setStatus(0);
//            friend.setIsApply(1);
//            friend.setIsClear(0);
//            friend.setCreateDate(new Date());
//
//            this.save(friend);
//
//            friend = new Friends();
//            friend.setUid(destUid);
//            friend.setDestUid(uid);
//            friend.setStatus(0);
//            friend.setIsApply(0);
//            friend.setIsClear(0);
//            friend.setCreateDate(new Date());
//
//            this.save(friend);
//            return true;
//        }
//        friend = friends.get(0);
//        Integer status = friend.getStatus();
//        if (status == 1) {
//            throw new RuntimeException(ApiUtils.error("你们已是好友"));
//        } else if (status == 2) {
//            friend.setStatus(0);
//            friend.setIsApply(1);
//            friend.setIsClear(0);
//            this.update(friend);
//        } else if (status == 0) {
//            friend.setIsApply(1);
//            friend.setIsClear(0);
//            this.update(friend);
//        }
//
//        return true;
//    }
//
//    /**
//     * 更新好友状态
//     */
//    @Override
//    public boolean updateFriends(Long friendId, Integer status, Long uid) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", uid);
//        params.put("destUid", friendId);
//        List<Friends> friends = this.findList(params);
//        if (CollectionUtils.isEmpty(friends)) {
//            Friends friend = new Friends();
//            friend.setUid(uid);
//            friend.setDestUid(friendId);
//            friend.setStatus(status);
//            friend.setIsApply(0);
//            friend.setCreateDate(new Date());
//            this.save(friend);
//        } else {
//            Friends friend = friends.get(0);
//            friend.setStatus(status);
//            this.update(friend);
//        }
//
//        params = new HashMap<String, Object>();
//        params.put("uid", friendId);
//        params.put("destUid", uid);
//        friends = this.findList(params);
//        Friends destFriend = null;
//        if (CollectionUtils.isEmpty(friends)) {
//            // 添加好友
//            destFriend = new Friends();
//            destFriend.setUid(friendId);
//            destFriend.setDestUid(uid);
//            destFriend.setStatus(status);
//            destFriend.setIsApply(0);
//            destFriend.setCreateDate(new Date());
//            this.save(destFriend);
//        } else {
//            // 删除好友
//            destFriend = friends.get(0);
//            destFriend.setStatus(status);
//            this.update(destFriend);
//        }
//
//        return true;
//    }

//    @Override
//    public List<FriendsDto> list(Long uid) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", uid);
//        params.put("status", 1);
//        List<Friends> friends = this.findList(params);
//        if (CollectionUtils.isEmpty(friends)) {
//            return null;
//        }
//        List<FriendsDto> dtos = FriendsDto.forList(friends);
//        List<Long> destUids = new ArrayList<Long>();
//        for (FriendsDto dto : dtos) {
//            destUids.add(dto.getDestUid());
//        }
//        List<TUserIdx> users = tUserIdxService.findInId(destUids);
//        for (FriendsDto dto : dtos) {
//            for (TUserIdx user : users) {
//                if (dto.getDestUid().intValue() == user.getId().intValue()) {
//                    dto.setDestUser(user);
//                    break;
//                }
//            }
//        }
//        return dtos;
//    }

//    /**
//     * 查询好友关系
//     *
//     * @param destUid
//     * @param uid
//     * @return
//     */
//    public Map<Long, Integer> matchReleation(List<Long> destUid, Long uid) {
//        List<Friends> releation = friendsDao.findIn(destUid, uid);
//        Map<Long, Integer> params = new HashMap<>();
//        for (Friends item : releation) {
//            params.put(item.getDestUid(), item.getStatus());
//        }
//        return params;
//    }
//
//    @Override
//    public List<FriendsDto> applyList(Long uid) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("uid", uid);
//        params.put("unStatus", 2);
//        params.put("isClear", 0);
//        List<Friends> friends = this.findList(params);
//        if (CollectionUtils.isEmpty(friends)) {
//            return null;
//        }
//        List<FriendsDto> dtos = FriendsDto.forList(friends);
//
//        if (dtos.size() == 0) {
//            return dtos;
//        }
//
//        List<Long> destUids = new ArrayList<Long>();
//        for (FriendsDto dto : dtos) {
//            destUids.add(dto.getDestUid());
//        }
//
//        params = new HashMap<String, Object>();
//        params.put("destUids", destUids);
//        List<TUserIdx> users = tUserIdxService.findList(params);
//
//        params = new HashMap<String, Object>();
//        params.put("destUid", uid);
//        params.put("unStatus", 2);
//        params.put("isApply", 1);
//        friends = this.findList(params);
//        for (FriendsDto dto : dtos) {
//            for (TUserIdx user : users) {
//                if (dto.getDestUid().intValue() == user.getId().intValue()) {
//                    dto.setDestUser(user);
//                }
//            }
//            for (Friends friend : friends) {
//                if (friend.getUid().intValue() == dto.getDestUid().intValue()) {
//                    dto.setIsApply(0);
//                }
//            }
//        }
//        return dtos;
//    }

//    @Override
//    public boolean clearApply(Long uid) {
//        friendsDao.clearApply(uid);
//        return true;
//    }

}
