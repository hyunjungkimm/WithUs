package com.example.fundingapi.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFunding is a Querydsl query type for Funding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFunding extends EntityPathBase<Funding> {

    private static final long serialVersionUID = 1806749459L;

    public static final QFunding funding = new QFunding("funding");

    public final NumberPath<Integer> fundingAmount = createNumber("fundingAmount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> fundingDate = createDateTime("fundingDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFunding(String variable) {
        super(Funding.class, forVariable(variable));
    }

    public QFunding(Path<? extends Funding> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFunding(PathMetadata metadata) {
        super(Funding.class, metadata);
    }

}

