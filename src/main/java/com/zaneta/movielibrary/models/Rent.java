package com.zaneta.movielibrary.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {
    int userId;
    int movieId;
    int id;
    String dateOfRenting;
    Date dateOfReturn;
    Date expectingDateToReturn;
    int isRented;
}


