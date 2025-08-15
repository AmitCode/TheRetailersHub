package com.intoThe.errorResponse;

import java.time.LocalDateTime;

public class SuppliersExceptionResponse {
    private int status;
    private String StatusMsg;
    private LocalDateTime errorTimeStamp;

    public SuppliersExceptionResponse(int status, String statusMsg) {
        this.status = status;
        StatusMsg = statusMsg;
        this.errorTimeStamp = LocalDateTime.now();
    }
}
