package com.depromeet.sulsul.domain.recordFlavor.entity;

import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.record.entity.Record;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordFlavor {
    @Id
    @Column(name = "beer_flavor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flavor_id")
    private Flavor flavor;

    public RecordFlavor(Long id, Record record, Flavor flavor) {
        this.id = id;
        this.record = record;
        this.flavor = flavor;
    }
}
