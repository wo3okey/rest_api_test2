package com.example.rest_api_test2.ext;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class TestObject {
    int id;
    String name;
    List<String> likeList;

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", likeList=" + likeList +
                '}';
    }
}
