package com.drinkhere.drinklybatch.feign.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignResponse<T> {
    

    private Result result;
    private T payload;

    @Getter
    @Setter
    public static class Result {
        private int code;
        private String message;
    }
}
