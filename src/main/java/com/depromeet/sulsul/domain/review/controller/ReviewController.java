package com.depromeet.sulsul.domain.review.controller;

import com.depromeet.sulsul.domain.review.dto.ReviewRequest;
import com.depromeet.sulsul.domain.review.dto.ReviewUpdateRequest;
import com.depromeet.sulsul.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> delete(@PathVariable("reviewId") Long reviewId){
        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
