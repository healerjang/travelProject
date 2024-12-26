package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.QMember;
import com.busanit501.travelproject.domain.QReservation;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ReservationByOrderRepositoryImpl extends QuerydslRepositorySupport implements ReservationByOrderRepository {
    public ReservationByOrderRepositoryImpl() {super(Reservation.class);}

    @Override
    public Page<Reservation> selectReservationUser(Long memberNo, ReservationOrder reservationOrder, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(reservation.member.memberNo.eq(memberNo));
        booleanBuilder.and(reservation.reservationOrder.eq(reservationOrder));
        query.where(booleanBuilder);
        query.orderBy(reservation.reservationNo.desc());
        this.getQuerydsl().applyPagination(pageable, query);
        List<Reservation> reservationList = query.fetch();
        long total = query.fetchCount();
        return new PageImpl<>(reservationList, pageable, total);
    }

    @Override
    public Page<Reservation> selectReservationAdmin(Long productNo, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(reservation.product.productNo.eq(productNo));
        query.where(booleanBuilder);
        query.orderBy(reservation.reservationNo.desc());
        this.getQuerydsl().applyPagination(pageable, query);
        List<Reservation> reservationList = query.fetch();
        long total = query.fetchCount();
        return new PageImpl<>(reservationList, pageable, total);
    }
}
