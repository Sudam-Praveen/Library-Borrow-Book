package org.example.repository;

import org.example.entity.BorrowBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BorrowBookRepo extends JpaRepository<BorrowBookEntity,Long> {

    public Boolean existsByBorrowerName(String borrowerName);
    public List<BorrowBookEntity> findByBorrowerName(String borrowerName);
}
