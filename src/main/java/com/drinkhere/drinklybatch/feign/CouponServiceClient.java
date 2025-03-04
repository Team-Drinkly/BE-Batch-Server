package com.drinkhere.drinklybatch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "coupon-service", url = "https://drinkly.store/api/v1/payment/m")
public interface CouponServiceClient {

    /**
     * 만료된 쿠폰 ID 목록 조회
     */
    @GetMapping("/coupons/expired")
    List<Long> getExpiredCoupons();

    /**
     * 만료된 쿠폰 상태 변경 요청
     */
    @PostMapping("/coupons/expire")
    void expireCoupons(@RequestBody List<Long> couponIds);
}

