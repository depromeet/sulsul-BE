package com.depromeet.sulsul.domain.flavor.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flavor {

    @Id
    @Column(name = "flavor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
}
