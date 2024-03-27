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
    private List<Long> books;
    private Date date;
    private double fine;

}
