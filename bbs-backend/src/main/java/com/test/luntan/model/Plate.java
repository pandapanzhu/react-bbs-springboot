package com.test.luntan.model;

import javax.persistence.*;

/**
 * 板块实体
 */
@Entity
@Table(name = "plate")
public class Plate extends BaseModel{


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //板块名称
    @Column(name = "platename")
    private String plateName;

    //板块排序
    @Column(name = "sort")
    private int sort;

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
