package com.gamal.javaeval.model;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class CustomError{
    private Timestamp timestamp;
    private int codigo;
    private String detail;
}
