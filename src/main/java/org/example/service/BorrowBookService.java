package org.example.service;

import org.example.Dto.BorrowBook;
import org.example.entity.BorrowBookEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BorrowBookService {
    ResponseEntity<?> saveDetails(BorrowBook borrowBook);
    List<BorrowBookEntity> getAll();
    public ResponseEntity<?> isBorrowerExist(String borrowerName);
    public Double calculateFineValue(BorrowBook borrowBook);

}
