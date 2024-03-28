package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BorrowBook {
    private Long id;
    private Long borrowerID;
    private String borrowerName;
    private String borrowerEmail;
    private List<Long> books;
    private List<String> bookTitles;
    private Date date;
    private String status;
    private double fine;

}
