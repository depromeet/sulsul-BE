package com.depromeet.sulsul.domain.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Beer {

    @Id
    @Column(name = "beer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "beer")
    private List<Review> reviews = new ArrayList<>();

    private String name;
    private String pictureUrl;
    private String content;
    private Float alcohol;
    private Integer price;
    private Integer volume;

}
