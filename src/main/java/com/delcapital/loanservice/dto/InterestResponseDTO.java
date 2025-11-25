package com.delcapital.loanservice.dto;

public class InterestResponseDTO {

    private int processed;
    private int skipped;
    private String message;

    public InterestResponseDTO(int processed, int skipped, String message) {
        this.processed = processed;
        this.skipped = skipped;
        this.message = message;
    }

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

