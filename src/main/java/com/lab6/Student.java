/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lab6;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author acer
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    int regID;
    String Name;
    String Address;
    String ParentName;
    String Phone;
    String Standard;
    Date RegDate;
}
