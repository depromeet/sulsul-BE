package com.depromeet.sulsul.domain.review.entity;

import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beer_id")
    private Beer beer;

    private String content;

    private Boolean isDeleted = false;

    public Review(String content, Member member, Beer beer) {
        this.content = content;
        this.member = member;
        this.beer = beer;
    }

    public Review(Long id, String content, Boolean isDeleted, Member member, Beer beer) {
        this.id = id;
        this.content = content;
        this.isDeleted = isDeleted;
        this.member = member;
        this.beer = beer;
    }

    public void updateReview(String content) {
        this.content = content;
    }

    public void deleteReview(){
        this.isDeleted = true;
    }

}
