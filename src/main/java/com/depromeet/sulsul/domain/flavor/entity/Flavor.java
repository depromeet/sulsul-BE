package com.depromeet.sulsul.domain.flavor.entity;

import com.depromeet.sulsul.domain.record.entity.RecordFlavor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flavor {

    @Id
    @Column(name = "flavor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToMany(mappedBy = "flavor")
    private List<RecordFlavor> recordFlavors = new ArrayList<>();
}
