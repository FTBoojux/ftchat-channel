package com.boojux.ftchatchannel.repository;
import com.boojux.ftchatchannel.bean.domain.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {
}

