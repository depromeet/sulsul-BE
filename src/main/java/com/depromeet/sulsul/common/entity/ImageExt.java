package com.depromeet.sulsul.common.entity;

public enum ImageExt {
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    GIF("gif");

    private String name;


    ImageExt(String name) {
        this.name = name;
    }
}
