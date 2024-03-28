package org.example.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Dto.BorrowBook;
import org.example.entity.BorrowBookEntity;
import org.example.repository.BorrowBookRepo;
import org.example.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if (borrowBookEntity.getStatus().equals("borrowed") ) {
                borrowingList.add(borrowBookEntity);
            }
        }
        return borrowingList;
    }

    public ResponseEntity<?> isBorrowerExist(String borrowerName){
        BorrowBookEntity byBorrowerName = borrowBookRepo.findByBorrowerName(borrowerName);
        if(byBorrowerName!=null){

            if(byBorrowerName.getStatus().equals("borrowed")){
                return ResponseEntity.status(HttpStatus.OK).body(true);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(false);
            }
        }else{
             return ResponseEntity.status(HttpStatus.OK).body(false);
        }


    }
}
