package com.busanit501.travelproject.repository.productJh1;

import com.busanit501.travelproject.domain.Product;
import com.busanit501.travelproject.domain.QLocation;
import com.busanit501.travelproject.domain.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@SuppressWarnings("unused")
public class ProductJh1SearchImpl extends QuerydslRepositorySupport implements ProductJh1Search {
  public ProductJh1SearchImpl() {
    super(Product.class);
  }

  @Override
  public Page<Product> searchProducts(Pageable pageable, String[] searchFor, String search) {
    QProduct product = QProduct.product;
    QLocation location = QLocation.location;
    JPQLQuery<Product> productJPQLQuery = this.from(product);

    productJPQLQuery.leftJoin(location).on(location.eq(product.location));

    if (search != null && !search.isEmpty() && searchFor != null && searchFor.length > 0) {

      BooleanBuilder bb = new BooleanBuilder();

      for (String sFor : searchFor) {
        switch (sFor) {
          case "name":
            bb.or(product.name.contains(search));
            break;
          case "description":
            bb.or(product.description.contains(search));
            break;
          case "location":
            bb.or(location.city.contains(search));
            bb.or(location.country.contains(search));
            break;
        }
      }

      productJPQLQuery.where(bb);
    }

    this.getQuerydsl().applyPagination(pageable, productJPQLQuery);

    List<Product> products = productJPQLQuery.fetch();

    long count = productJPQLQuery.fetchCount();

    return new PageImpl<>(products, pageable, count);
  }
}
