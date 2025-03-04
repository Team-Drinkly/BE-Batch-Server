package com.drinkhere.drinklybatch.service;

import com.drinkhere.drinklybatch.feign.CouponServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CouponBatchService {

    private final CouponServiceClient couponServiceClient;

    /**
     * 매일 정오(12:00 PM)에 만료된 쿠폰 처리 배치 실행
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void processExpiredCoupons() {
        log.info("🔍 쿠폰 만료 배치 실행 시작");

        // 만료된 쿠폰 ID 조회
        List<Long> expiredCouponIds = couponServiceClient.getExpiredCoupons();

        if (!expiredCouponIds.isEmpty()) {
            // 만료 처리 요청
            couponServiceClient.expireCoupons(expiredCouponIds);
            log.info("쿠폰 만료 처리 완료: {}", expiredCouponIds);
        } else {
            log.info("만료된 쿠폰 없음");
        }
    }
}

