package com.depromeet.sulsul.domain.records.service;

import com.depromeet.sulsul.common.dto.ImageDto;
import com.depromeet.sulsul.common.entity.ImageType;
import com.depromeet.sulsul.common.external.AwsS3ImageClient;
import com.depromeet.sulsul.common.response.dto.PageableResponse;
import com.depromeet.sulsul.domain.beer.entity.Beer;
import com.depromeet.sulsul.domain.beer.repository.BeerRepository;
import com.depromeet.sulsul.domain.flavor.entity.Flavor;
import com.depromeet.sulsul.domain.member.dto.MemberRecordsDto;
import com.depromeet.sulsul.domain.member.entity.Member;
import com.depromeet.sulsul.domain.member.repository.MemberRepository;
import com.depromeet.sulsul.domain.records.dto.RecordsDto;
import com.depromeet.sulsul.domain.records.dto.RecordsFindRequest;
import com.depromeet.sulsul.domain.records.dto.RecordsRequest;
import com.depromeet.sulsul.domain.records.entity.Records;
import com.depromeet.sulsul.domain.records.repository.RecordsRepository;
import com.depromeet.sulsul.domain.recordsFlavor.entity.RecordsFlavor;
import com.depromeet.sulsul.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static com.depromeet.sulsul.util.PaginationUtil.PAGINATION_SIZE;
import static com.depromeet.sulsul.util.PaginationUtil.isOverPaginationSize;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class RecordsService {

    private final RecordsRepository recordsRepository;
    private final BeerRepository beerRepository;
    private final MemberRepository memberRepository;
    private final AwsS3ImageClient awsS3ImageClient;

    public ImageDto uploadImage(MultipartFile multipartFile) {
        if (!ImageUtil.isValidExtension(multipartFile.getOriginalFilename())) {
            throw new IllegalArgumentException("[ERROR] Not supported file format.");
        }
        return new ImageDto(awsS3ImageClient.upload(multipartFile, ImageType.RECORD));
    }

    @Transactional
    public Records save(RecordsRequest recordRequest){
        Long memberIdTemp = 1L;
        Member member = memberRepository.getById(memberIdTemp);
        Beer beer = beerRepository.getById(recordRequest.getBeerId());
        return recordsRepository.save(new Records(
                member, beer, recordRequest
        ));
    }

    public PageableResponse<RecordsDto> findAllRecordsWithPageable(RecordsFindRequest recordsFindRequest){
        List<Records> allRecordsWithPageable = recordsRepository.findAllRecordsWithPageable(recordsFindRequest);
        List<RecordsDto> allRecordDtosWithPageable = new ArrayList<>();
        PageableResponse<RecordsDto> recordDtoPageableResponse = new PageableResponse<>();
        for (Records records : allRecordsWithPageable) {
            List<RecordsFlavor> recordFlavors = records.getRecordsFlavors();
            List<Flavor> flavors = new ArrayList<>();
            for (RecordsFlavor recordFlavor : recordFlavors) {
                flavors.add(recordFlavor.getFlavor());
            }
            MemberRecordsDto memberRecordsDto = new MemberRecordsDto(records.getMember().getId(), records.getMember().getName());

            allRecordDtosWithPageable.add(new RecordsDto(records.getContent(), memberRecordsDto,
                    records.getFeel(), records.getScore(), flavors, records.getCreatedDate(), records.getModifiedDate()));
        }
        if(isOverPaginationSize(allRecordDtosWithPageable)){
            allRecordDtosWithPageable.remove(PAGINATION_SIZE);
            recordDtoPageableResponse.setHasNext(true);
        }
        recordDtoPageableResponse.setContents(allRecordDtosWithPageable);
        return recordDtoPageableResponse;
    }
}
