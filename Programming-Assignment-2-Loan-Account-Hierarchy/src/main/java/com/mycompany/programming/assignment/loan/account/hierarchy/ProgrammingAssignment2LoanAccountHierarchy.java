/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programming.assignment.loan.account.hierarchy;

/**
 * @author dheetya
 */
public class ProgrammingAssignment2LoanAccountHierarchy {

    public static void main(String[] args) {
        System.out.println("run:");

        //three different loan objects, one of each type.
        CarLoan carLoan = new CarLoan(25000.00, 4.25, 72, "IRQ3458977");

        Address propertyAddress = new Address("321 Main Street", "State College", "PA", "16801");
        PrimaryMortgage propertyLoan = new PrimaryMortgage(250000.00, 3.1, 360, 35.12, propertyAddress);

        UnsecuredLoan unsecuredLoan = new UnsecuredLoan(5000.00, 10.75, 48);
        System.out.format("%n%s%s%s%n", carLoan, propertyLoan, unsecuredLoan);
    }
}

/**
 *Address class stores information about a physical address.
 */
class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String zipcode;

    /**
     * Constructor for the Address class.
     * @param street The street address.
     * @param city The city.
     * @param state The state.
     * @param zipcode The zip code.
     */
    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    /**
     * Returns a string representation of the address.
     * @return formatted address string.
     */
    @Override
    //public String toString() {
        //return String.format("%s\n%s, %s %s", street, city, state, zipcode);
    //}

    public String toString() {
    //\t to indent each line
        return String.format("\t%s\n\t%s, %s %s", street, city, state, zipcode);
}
}


/**
 * This class is a general representation of a loan.
 */
class LoanAccount {
    private final double principal;
    private final double annualInterestRate;
    private final int months;

    /**
     * Constructor for the LoanAccount class.
     * @param principal original loan amount.
     * @param annualInterestRate annual interest rate.
     * @param months how long the loan is in months.
     */
    public LoanAccount(double principal, double annualInterestRate, int months) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.months = months;
    }

    // Getters
    public double getPrincipal() {
        return principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public int getMonths() {
        return months;
    }

    /**
     * Calculates the fixed monthly payment for the loan.
     *M = P * [r(1+r)^n] / [(1+r)^n - 1]
     * @return The monthly payment amount.
     */
    public double calculateMonthlyPayment() {
        double monthlyRate = annualInterestRate / 100.0 / 12.0;
        //case for 0% interest
        if (monthlyRate == 0) {
            return principal / months;
        }
        double termFactor = Math.pow(1 + monthlyRate, months);
        return principal * (monthlyRate * termFactor) / (termFactor - 1);
    }

    /**
     * Returns a string with the basic loan information.
     * @return The basic loan information string.
     */
    @Override
    public String toString() {
        return String.format("""
                             Principal: $%.2f
                             Annual Interest Rate: %.2f%%
                             Term of Loan in Months: %d""",
                principal,
                annualInterestRate,
                months
        );
    }
}


/**
 * The CarLoan class represents a loan for a vehicle.
 * subclass of LoanAccount.
 */
class CarLoan extends LoanAccount {
    private final String vehicleVIN;

    /**
     * Constructor for the CarLoan class.
     * @param principal  loan amount.
     * @param annualInterestRate  annual interest rate.
     * @param months  term in months.
     * @param vehicleVIN  Vehicle Identification Number.
     */
    public CarLoan(double principal, double annualInterestRate, int months, String vehicleVIN) {
        super(principal, annualInterestRate, months);
        this.vehicleVIN = vehicleVIN;
    }

    /**
     * Returns a string with all car loan details.
     * @return The car loan details string.
     */
    @Override
    public String toString() {
        return String.format("""
                             Car Loan with:
                             %s
                             Monthly Payment: $%.2f
                             Vehicle VIN: %s
                             
                             """,
                super.toString(),
                calculateMonthlyPayment(),
                vehicleVIN
        );
    }
}


/**
 * The PrimaryMortgage class represents a mortgage loan.
 *subclass of LoanAccount.
 */
class PrimaryMortgage extends LoanAccount {
    private final double PMIMonthlyAmount;
    private final Address address;

    /**
     * Constructor for the PrimaryMortgage class.
     * @param principal  loan amount.
     * @param annualInterestRate  annual interest rate.
     * @param months  term in months.
     * @param PMIMonthlyAmount  monthly PMI amount.
     * @param address  property address object.
     */
    public PrimaryMortgage(double principal, double annualInterestRate, int months, double PMIMonthlyAmount, Address address) {
        super(principal, annualInterestRate, months);
        this.PMIMonthlyAmount = PMIMonthlyAmount;
        this.address = address;
    }

    /**
     * Returns string with all mortgage details.
     * @return The mortgage loan details string.
     */
    @Override
    public String toString() {
        return String.format("""
                             Primary Mortgage Loan with:
                             %s
                             Monthly Payment: $%.2f
                             PMI Monthly Amount: $%.2f
                             Property Address:
                             %s
                             
                             """,
                super.toString(),
                calculateMonthlyPayment(),
                PMIMonthlyAmount,
                address.toString()
        );
    }
}


/**
 * this class represents a general-purpose loan without collateral.
 * subclass of LoanAccount.
 */
class UnsecuredLoan extends LoanAccount {

    /**
     * Constructor for the UnsecuredLoan class.
     * @param principal  loan amount.
     * @param annualInterestRate  annual interest rate.
     * @param months  term in months.
     */
    public UnsecuredLoan(double principal, double annualInterestRate, int months) {
        super(principal, annualInterestRate, months);
    }

    /**
     * Returns string with all unsecured loan details.
     * @return the unsecured loan details string.
     */
    @Override
    public String toString() {
        return String.format("""
                             Unsecured Loan with:
                             %s
                             Monthly Payment: $%.2f""",
                super.toString(),
                calculateMonthlyPayment()
        );
    }
}