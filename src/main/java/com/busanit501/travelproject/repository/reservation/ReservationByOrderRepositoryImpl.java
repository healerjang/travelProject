package com.busanit501.travelproject.repository.reservation;

import com.busanit501.travelproject.domain.*;
import com.busanit501.travelproject.domain.common.ReservationOrder;
import com.busanit501.travelproject.dto.ProductJh1DTO;
import com.busanit501.travelproject.dto.reservation.ReservationDTO;
import com.busanit501.travelproject.dto.reservation.ReservationUserDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ReservationByOrderRepositoryImpl extends QuerydslRepositorySupport implements ReservationByOrderRepository {
    public ReservationByOrderRepositoryImpl() {
        super(Reservation.class);
    }

    @Override
    public Page<ReservationUserDTO> selectReservationUser(Long memberNo, ReservationOrder reservationOrder, Pageable pageable) {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(reservation.member.memberNo.eq(memberNo));
        booleanBuilder.and(reservation.reservationOrder.eq(reservationOrder));
        query.where(booleanBuilder);
        query.orderBy(reservation.reservationNo.desc());
        this.getQuerydsl().applyPagination(pageable, query);
        JPQLQuery<Tuple> tupleQuery = query.select(
                reservation,
                reservation.member.memberNo,
                reservation.product.name,
                reservation.product.imagePath,
                reservation.product.description,
                reservation.product.price,
                reservation.product.startDate,
                reservation.product.endDate,
                reservation.product.location.country,
                reservation.product.location.city
        );
        List<Tuple> tupleList = tupleQuery.fetch();
        List<ReservationUserDTO> dtoList = tupleList.stream().map(tuple -> {
           Reservation reservation1 = tuple.get(0, Reservation.class);
           Long memberNo1 = tuple.get(1, Long.class);
           String productName = tuple.get(2, String.class);
           String productImgPath = tuple.get(3, String.class);
           String productDescription = tuple.get(4, String.class);
           Long productPrice = tuple.get(5, Long.class);
           LocalDate productStartDate = tuple.get(6, LocalDate.class);
           LocalDate productEndDate = tuple.get(7, LocalDate.class);
           String productLocationCountry = tuple.get(8, String.class);
           String productLocationCity = tuple.get(9, String.class);
           ReservationUserDTO reservationUserDTO = ReservationUserDTO.builder()
                   .reservationNo(reservation1.getReservationNo())
                   .memberNo(memberNo1)
                   .productName(productName)
                   .imagePath(productImgPath)
                   .productDescription(productDescription)
                   .productPrice(productPrice)
                   .productStartDate(productStartDate)
                   .productEndDate(productEndDate)
                   .productLocationCountry(productLocationCountry)
                   .productLocationCity(productLocationCity)
                   .ReservationOrder(reservation1.getReservationOrder())
                   .build();
           return reservationUserDTO;
        }).collect(Collectors.toList());
        long total = query.fetchCount();
        return new PageImpl<>(dtoList, pageable, total);
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

    @Override
    public List<Long> bestReservationProducts() {
        QReservation reservation = QReservation.reservation;
        JPQLQuery<Reservation> query = from(reservation);
        List<Long> result = query.select(reservation.product.productNo)
                .groupBy(reservation.product.productNo)
                .orderBy(reservation.product.productNo.count().desc())
                .limit(10).fetch();
        log.info(result + "레포 확인");
        return result;
    }
}
