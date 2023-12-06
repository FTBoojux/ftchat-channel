package com.boojux.ftchatchannel.repository;

import com.boojux.ftchatchannel.bean.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByConversation(int conversationId);
}
