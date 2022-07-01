package com.depromeet.sulsul.domain.record.entity;

import com.depromeet.sulsul.common.entity.BaseEntity;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.record.dto.RecordRequestDto;
import com.depromeet.sulsul.domain.record.dto.RecordUpdateRequestDto;
import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"member", "beer", "recordFlavors"})
@EqualsAndHashCode(exclude = {"member", "beer", "recordFlavors"})
public class Record extends BaseEntity {

  @Id
  @Column(name = "record_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "beer_id")
  private Beer beer;

  @OneToMany(mappedBy = "record")
  private List<RecordFlavor> recordFlavors = new ArrayList<>();

  private String content;
  private Boolean isPublic;
  private Integer feel;
  private String startCountryKor;
  private String startCountryEng;
  private String endCountryKor;
  private String endCountryEng;
  private String imageUrl;

  @Builder
  public Record(String content, Boolean isPublic, Integer feel) {
    this.content = content;
    this.isPublic = isPublic;
    this.feel = feel;
  }

  public Record(Member member, Beer beer, RecordRequestDto recordRequestDto) {
    this.member = member;
    this.beer = beer;
    this.content = recordRequestDto.getContent();
    this.isPublic = recordRequestDto.getIsPublic();
    this.feel = recordRequestDto.getFeel();
  }

  private void updateStartCountryName(Record record) {
    if (record.getEndCountryEng().isBlank() || record.getEndCountryKor().isBlank()) {
      this.startCountryKor = "대한민국";
      this.startCountryEng = "KOR";
      return;
    }
    this.startCountryKor = record.getEndCountryKor();
    this.startCountryEng = record.getEndCountryEng();
  }

  private void initializeStartCountryName() {
    this.startCountryKor = "대한민국";
    this.startCountryEng = "KOR";
  }

  private void updateEndCountry(Beer beer) {
    if (beer.getCountry().getNameKor().isBlank() || beer.getCountry().getNameEng().isBlank()) {
      this.endCountryKor = "대한민국";
      this.endCountryEng = "KOR";
      return;
    }
    this.endCountryKor = beer.getCountry().getNameKor();
    this.endCountryEng = beer.getCountry().getNameEng();
  }

  private void updateBeer(Beer beer) {
    this.beer = beer;
  }

  private void updateMember(Member member) { this.member = member; }

  public void initializeRecordWithoutLastRecord(Beer beer, Member member){
    this.updateBeer(beer);
    this.updateEndCountry(beer);
    this.initializeStartCountryName();
    this.updateMember(member);
  }

  public void initializeRecord(Beer beer, Record lastSavedRecord, Member member){
    this.updateBeer(beer);
    this.updateEndCountry(beer);
    this.updateStartCountryName(lastSavedRecord);
    this.updateMember(member);
  }

  public void updateRecord(RecordUpdateRequestDto recordUpdateRequestDto, List<RecordFlavor> recordFlavors){
    if(recordUpdateRequestDto.getIsPublic() != null)
      this.isPublic = recordUpdateRequestDto.getIsPublic();
    if(!StringUtils.isBlank(recordUpdateRequestDto.getContent()))
      this.content = recordUpdateRequestDto.getContent();
    if(recordUpdateRequestDto.getFeel() != null)
      this.feel = recordUpdateRequestDto.getFeel();
    if(!StringUtils.isBlank(recordUpdateRequestDto.getImageUrl()))
      this.imageUrl = recordUpdateRequestDto.getImageUrl();
    if(recordFlavors != null)
      this.recordFlavors = recordFlavors;
  }

}