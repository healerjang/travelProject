package com.busanit501.travelproject.repository.ProductCgw;

import com.busanit501.travelproject.domain.Location;
import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.util.List;

public class ProductCgwSearchImpl extends QuerydslRepositorySupport implements ProductCgwSearch {

    public ProductCgwSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> searchProduct(Location location, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);

        BooleanBuilder builder = new BooleanBuilder();

        if (location != null) builder.and(product.location.eq(location));
        if (startDate != null) builder.and(product.startDate.eq(startDate));
        if (endDate != null) builder.and(product.endDate.eq(endDate));

        query.where(builder);

        this.getQuerydsl().applyPagination(pageable, query);

        List<Product> results = query.fetch();
        long total = query.fetchCount();

        return new PageImpl<>(results, pageable, total);
    }
}
