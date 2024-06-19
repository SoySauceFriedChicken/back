package com.example.Foodle.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Foodle.entity.FriendEntity;
import com.example.Foodle.entity.UserEntity;
import com.example.Foodle.repository.UserRepository;

@Service
public class FriendService {

    private final UserRepository userRepository;

    @Autowired
    public FriendService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<FriendEntity> getFriends(String username) {
        UserEntity user = userRepository.findByuid(username);
        if (user != null) {
            String friendIds = user.getFriendIds();
            if (friendIds != null && !friendIds.isEmpty()) {
                List<String> friendIdList = Arrays.asList(friendIds.split(","));
                List<FriendEntity> friends = new ArrayList<>();
                for (String friendId : friendIdList) {
                    // 친구의 정보를 데이터베이스에서 조회하는 로직이 필요하다면 추가
                    friends.add(new FriendEntity(friendId));
                }
                return friends;
            }
        }
        return new ArrayList<>();
    }
}