package com.drinkhere.drinklybatch.service;

import com.drinkhere.drinklybatch.feign.MemberServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionBatchService {

    private final MemberServiceClient memberServiceClient;

    /**
     * 매일 정오(12:00 PM)에 구독 만료 배치 실행
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void processExpiredSubscriptions() {
        log.info("🔍 구독 만료 배치 실행 시작");

        // 만료된 구독 조회
        List<Long> expiredMemberIds = memberServiceClient.getExpiredSubscriptions();

        if (!expiredMemberIds.isEmpty()) {
            // 만료 처리 요청
            memberServiceClient.expireSubscriptions(expiredMemberIds);
            log.info("구독 만료 처리 완료: {}", expiredMemberIds);
        } else {
            log.info("만료된 구독 없음");
        }
    }
}
