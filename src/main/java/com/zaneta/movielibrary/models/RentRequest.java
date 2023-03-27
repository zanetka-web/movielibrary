package com.zaneta.movielibrary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentRequest {
    int userId;
    int movieId;
}
