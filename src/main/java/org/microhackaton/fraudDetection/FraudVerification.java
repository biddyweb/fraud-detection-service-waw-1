package org.microhackaton.fraudDetection;

public class FraudVerification {
    public String firstName;
    public String lastName;
    public String fraudStatus;
    public String job;
    public String amount;

    public FraudVerification(String firstName, String lastName, String fraudStatus, String job, String amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fraudStatus = fraudStatus;
        this.job = job;
        this.amount = amount;
    }
}
