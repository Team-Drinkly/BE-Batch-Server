package com.drinkhere.drinklybatch.feign;

import com.drinkhere.drinklybatch.feign.response.FeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "member-service", url = "https://drinkhere.store/api/v1/member/m")
public interface MemberServiceClient {

    /**
     * 만료된 구독 목록 조회
     */
    @GetMapping("/expired-subscriptions")
    FeignResponse<List<Long>> getExpiredSubscriptions();

    /**
     * 구독 만료 처리
     */
    @PostMapping("/expire-subscriptions")
    void expireSubscriptions(@RequestBody List<Long> expiredMemberIds);
}
