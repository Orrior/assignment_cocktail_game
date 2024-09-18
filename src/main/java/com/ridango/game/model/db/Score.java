package com.ridango.game.model.db;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SCOREBOARD")
@Getter
@Setter
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Generated
    String name;
    int score;

    public Score(){}
    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }
}
