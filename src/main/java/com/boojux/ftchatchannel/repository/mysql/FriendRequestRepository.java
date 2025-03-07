package com.boojux.ftchatchannel.repository.mysql;
import com.boojux.ftchatchannel.bean.domain.mysql.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, String> {
}

