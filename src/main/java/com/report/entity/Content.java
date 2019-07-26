package com.report.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "content")
public class Content {
    @Id
    @JsonIgnore
    long id;
    String name;
    Integer type;


    String content;
    String setNull;


}
