package com.report.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: Kris
 * @Date: 2019-07-16  15:24
 */
@Data
@Table
@Entity
public class PDFBean {
    @Id
    long id;
    String full_name;
    String name;
    String content;
    String result;
}
