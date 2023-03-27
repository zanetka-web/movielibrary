package com.zaneta.movielibrary.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentReport {
    String username;
    String movieName;
    String dateOfRenting;
    Date dateOfReturn;
    Date expectingDateToReturn;
    int isRented;
}
