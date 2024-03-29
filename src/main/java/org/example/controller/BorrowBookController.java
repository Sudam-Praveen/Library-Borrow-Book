package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Dto.BorrowBook;
import org.example.entity.BorrowBookEntity;
import org.example.service.BorrowBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/borrow")
@Slf4j
@RequiredArgsConstructor
public class BorrowBookController {
    final BorrowBookService borrowBookService;

    @PostMapping("/add-borrow-details")
    public ResponseEntity<?> addBorrowDetails(@RequestBody BorrowBook borrowBook) {
        log.info(borrowBook.toString());
        return ResponseEntity.ok(borrowBookService.saveDetails(borrowBook));
    }

    @GetMapping("/getAll-borrowed-details")
    public ResponseEntity<?> getAll() {
        List<BorrowBookEntity> all = borrowBookService.getAllBorrowedDetails();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/getAll-returned-details")
    public ResponseEntity<?> getReturnedAll() {
        List<BorrowBookEntity> all = borrowBookService.getAllReturnedDetails();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/find-exists-in-borrowedList/{borrowerName}")
    public ResponseEntity<?> findBorrower(@PathVariable String borrowerName) {

        return borrowBookService.isBorrowerExist(borrowerName);
    }
    @PostMapping("/find-the-fine")
    public Double fineValue(@RequestBody BorrowBook borrowBook){
         return borrowBookService.calculateFineValue(borrowBook);
    }
}
