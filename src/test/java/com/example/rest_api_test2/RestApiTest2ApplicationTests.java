package com.example.rest_api_test2;

import com.example.rest_api_test2.stest.RestTemplateApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RestApiTest2ApplicationTests {

    @Autowired
    public RestTemplateApi restTemplateApi;

    @Test
    void api_test() {
    }

}
