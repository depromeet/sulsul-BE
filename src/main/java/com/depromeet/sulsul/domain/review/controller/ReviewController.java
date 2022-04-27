package com.depromeet.sulsul.domain.review.controller;

import com.depromeet.sulsul.common.response.dto.ResponseDto;
import com.depromeet.sulsul.domain.review.dto.ReviewDeleteRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewUpdateRequest;
import com.depromeet.sulsul.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<Object> save(@RequestBody ReviewRequest reviewRequest){
        reviewService.save(reviewRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/review")
    public ResponseEntity<Object> update(@RequestBody ReviewUpdateRequest reviewUpdateRequest){
        reviewService.update(reviewUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/review")
    public ResponseEntity<Object> delete(@RequestBody ReviewDeleteRequest reviewDeleteRequest){
        reviewService.delete(reviewDeleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
