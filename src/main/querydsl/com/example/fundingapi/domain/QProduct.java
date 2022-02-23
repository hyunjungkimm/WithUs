package com.example.fundingapi.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 2006898949L;

    public static final QProduct product = new QProduct("product");

    public final StringPath finishDate = createString("finishDate");

    public final StringPath fundingStatus = createString("fundingStatus");

    public final NumberPath<Integer> fundingUserNumber = createNumber("fundingUserNumber", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath startDate = createString("startDate");

    public final NumberPath<Integer> targetFundingAmount = createNumber("targetFundingAmount", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> totalFundingAmount = createNumber("totalFundingAmount", Integer.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

