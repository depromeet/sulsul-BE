package com.depromeet.sulsul.domain.recordsFlavor.repository;


import com.depromeet.sulsul.domain.recordsFlavor.entity.RecordsFlavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordsFlavorRepository extends JpaRepository<RecordsFlavor, Long> {
}
