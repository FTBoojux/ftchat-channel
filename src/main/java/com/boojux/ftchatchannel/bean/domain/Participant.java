package com.boojux.ftchatchannel.bean.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "ftchat_participant")
@Getter
@Setter
@ToString
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String user;

    @Column(name = "conversation")
    private Integer conversation;

    @Column(name = "is_hidden")
    private Boolean isHidden;

}
