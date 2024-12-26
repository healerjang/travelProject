package com.busanit501.travelproject.repository.freeboard.search;


import com.busanit501.travelproject.domain.FreeBoard;
import com.busanit501.travelproject.domain.QFreeBoard;
import com.busanit501.travelproject.domain.QReply;
import com.busanit501.travelproject.dto.freeboard.FreeBoardListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// 반드시 이름 작성시: 인터페이스이름 + Impl
// QuerydslRepositorySupport 의무 상속,
// 만든 인터페이스 구현하기.
public class FreeBoardSearchImpl extends QuerydslRepositorySupport
        implements FreeBoardSearch {

    public FreeBoardSearchImpl() {
        super(FreeBoard.class);
    }

    @Override

    public Page<FreeBoard> search(Pageable pageable) {

        QFreeBoard freeBoard = QFreeBoard.freeBoard; // Q 도메인 객체, 엔티티 클래스(= 테이블)
        JPQLQuery<FreeBoard> query = from(freeBoard); // select * from food 한 결과와 비슷함.

        query.where(freeBoard.title.contains("3"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(freeBoard.title.contains("3"));
        booleanBuilder.or(freeBoard.content.contains("7"));

        query.where(booleanBuilder);

        query.where(freeBoard.freeBoardNo.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);


        List<FreeBoard> list = query.fetch();

        long total = query.fetchCount();


        return null;
    }

    @Override
    //String[] types , "t", "c", "tc"
    public Page<FreeBoard> searchAll(String[] types, String keyword, Pageable pageable) {
        QFreeBoard freeBoard = QFreeBoard.freeBoard;
        JPQLQuery<FreeBoard> query = from(freeBoard);
        // select * from
        if (types != null && types.length > 0 && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(freeBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(freeBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(freeBoard.member.memberID.contains(keyword));
                        break;




                }
            }

            query.where(booleanBuilder);
        }

        query.where(freeBoard.freeBoardNo.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<FreeBoard> list = query.fetch();

        long total = query.fetchCount();

        Page<FreeBoard> result = new PageImpl<FreeBoard>(list, pageable, total);

        return result;
    }

    @Override
    public Page<FreeBoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QFreeBoard freeBoard = QFreeBoard.freeBoard;
        QReply reply = QReply.reply;
        JPQLQuery<FreeBoard> query = from(freeBoard);// select * from

        query.leftJoin(reply).on(reply.freeBoard.freeBoardNo.eq(freeBoard.freeBoardNo));

        query.groupBy(freeBoard.freeBoardNo);

        // 위에서 사용한 검색 조건 , 재사용하기
        if (types != null && types.length > 0 && keyword != null) {
            // 여러 조건을 하나의 객체에 담기.
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(freeBoard.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(freeBoard.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(freeBoard.member.memberID.contains(keyword));
                        break;
                }
            }

            query.where(booleanBuilder);
        }

        query.where(freeBoard.freeBoardNo.gt(0L));

        JPQLQuery<FreeBoardListReplyCountDTO> dtoQuery =
                query.select(Projections.bean(FreeBoardListReplyCountDTO.class,
                        freeBoard.freeBoardNo,
                        freeBoard.title,
                        freeBoard.content,
                        freeBoard.member,
                        freeBoard.regDate,
                        reply.count().as("replyCount")));


        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<FreeBoardListReplyCountDTO> list = dtoQuery.fetch();

        long total = dtoQuery.fetchCount();

        Page<FreeBoardListReplyCountDTO> result = new PageImpl<FreeBoardListReplyCountDTO>(list, pageable, total);

        return result;

    }

    }


