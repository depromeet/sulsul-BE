package com.depromeet.sulsul.domain.recordFlavor.repository;


import com.depromeet.sulsul.domain.recordFlavor.entity.RecordFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsFlavorRepository extends JpaRepository<RecordFlavor, Long> {
}
