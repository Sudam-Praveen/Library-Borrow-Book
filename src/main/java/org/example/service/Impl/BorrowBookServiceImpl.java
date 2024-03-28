package org.example.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Dto.BorrowBook;
import org.example.entity.BorrowBookEntity;
import org.example.repository.BorrowBookRepo;
import org.example.service.BorrowBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowBookServiceImpl implements BorrowBookService {

    final BorrowBookRepo borrowBookRepo;
    final ObjectMapper objectMapper;


    @Override
    public ResponseEntity<?> saveDetails(BorrowBook borrowBook) {
        BorrowBookEntity borrowBookEntity = objectMapper.convertValue(borrowBook, BorrowBookEntity.class);
        BorrowBookEntity savedBooks = borrowBookRepo.save(borrowBookEntity);
        return ResponseEntity.status(HttpStatus.OK).body(savedBooks);
    }

    @Override
    public List<BorrowBookEntity> getAll() {
        List<BorrowBookEntity> borrowingList = new ArrayList<>();
        List<BorrowBookEntity> all = borrowBookRepo.findAll();
        for (BorrowBookEntity borrowBookEntity : all) {
            if (borrowBookEntity.getStatus().equals("borrowed")) {
                borrowingList.add(borrowBookEntity);
            }
        }
        return borrowingList;
    }

    public ResponseEntity<?> isBorrowerExist(String borrowerName) {
        List<BorrowBookEntity> byBorrowerName = borrowBookRepo.findByBorrowerName(borrowerName);

        if (byBorrowerName != null) {
            for (BorrowBookEntity borrower : byBorrowerName) {
                if (borrower.getStatus().equals("borrowed")) {
                    return ResponseEntity.status(HttpStatus.OK).body(true);
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(false);
                }
            }

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }


    public Double calculateFineValue(BorrowBook borrowBook) {
        LocalDate startDate = LocalDate.parse(borrowBook.getDate());
        LocalDate endDate = LocalDate.parse(borrowBook.getReturnedDate());
        long differenceInDays = ChronoUnit.DAYS.between(startDate, endDate);
        if (differenceInDays >= 14) {
            // Calculate the fine
            return (double) ((differenceInDays - 14) * 20);

        } else {
            // No fine if returned within 14 days
            return (double) 0.0;
        }
    }
}
