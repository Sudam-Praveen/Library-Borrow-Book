package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.Dto.BorrowBook;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/borrow")
@Slf4j
public class BorrowBookController {

    @PostMapping("/add-borrow-details")
    public void addBorrowDetails(@RequestBody BorrowBook borrowBook){
    log.info(borrowBook.toString());
    }
}
