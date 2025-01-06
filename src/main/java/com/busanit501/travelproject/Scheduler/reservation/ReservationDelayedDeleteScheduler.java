package com.busanit501.travelproject.Scheduler.reservation;

import com.busanit501.travelproject.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReservationDelayedDeleteScheduler {
    private final ReservationRepository reservationRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void delayedDelete() {
        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        reservationRepository.delayedDelete(sevenDaysAgo);
    }
}
