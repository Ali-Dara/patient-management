package com.dara.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

@Slf4j
@GrpcService
public class BillingGrpc extends BillingServiceImplBase {
    @Override
    public void createBilling(BillingRequest request, StreamObserver<BillingResponse> responseObserver) {
        log.info("createBilling request={}",request.toString());

        BillingResponse  response = BillingResponse.newBuilder()
                .setAccountId(String.valueOf(new  Random().nextLong()))
                .setStatus("Active")
                .build();
        log.info("createBilling response={}",response.toString());
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
