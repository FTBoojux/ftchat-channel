package com.boojux.ftchatchannel.repository.cassandra;

import com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus.MessageReadStatus;
import com.boojux.ftchatchannel.bean.domain.cassandra.MessageStatus.MessageStatusKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageReadStatusRepository extends CassandraRepository<MessageReadStatus, MessageStatusKey> {
}
