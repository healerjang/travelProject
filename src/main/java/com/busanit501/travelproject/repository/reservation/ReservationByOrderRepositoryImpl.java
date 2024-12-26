package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.QReservation;
import com.busanit501.travelproject.domain.Reservation;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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
    public Page<ReservationDTO> selectReservationUser(Long memberNo, ReservationOrder reservationOrder, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(reservation.member.memberNo.eq(memberNo));
        booleanBuilder.and(reservation.reservationOrder.eq(reservationOrder));
        query.where(booleanBuilder);
        query.orderBy(reservation.reservationNo.desc());
        JPQLQuery<ReservationDTO> dtoQuery = query.select(Projections.bean(ReservationDTO.class,
                reservation.reservationNo,
                reservation.product.productNo,
                reservation.member.memberNo,
                reservation.reservationOrder
                //regDate, modDate 생략
        ));
        this.getQuerydsl().applyPagination(pageable, dtoQuery);
        List<ReservationDTO> reservationList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();
        return new PageImpl<>(reservationList, pageable, total);
    }

    @Override
    public Page<ReservationDTO> selectReservationAdmin(Long productNo, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(reservation.product.productNo.eq(productNo));
        query.where(booleanBuilder);
        query.orderBy(reservation.reservationNo.desc());
        JPQLQuery<ReservationDTO> dtoQuery = query.select(Projections.bean(ReservationDTO.class,
                reservation.reservationNo,
                reservation.product.productNo,
                reservation.member.memberNo,
                reservation.reservationOrder
                //regDate, modDate 생략
        ));
        this.getQuerydsl().applyPagination(pageable, dtoQuery);
        List<ReservationDTO> reservationList = dtoQuery.fetch();
        long total = dtoQuery.fetchCount();
        return new PageImpl<>(reservationList, pageable, total);
    }
}
