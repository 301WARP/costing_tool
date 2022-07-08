package au.edu.utas.costing_tool.Model;

// =============================================================================
// External Imports
// =============================================================================

import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


// =============================================================================
// External Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.ExpenseType;


@Entity
@Table(name="expense")
@DiscriminatorValue("TRAVEL")
public class Travel extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="num_travellers")
    private Integer numTravellers;
    public Integer getNumTravellers() {return this.numTravellers;}
    public void setNumTravellers(Integer numTravellers)
        {this.numTravellers = numTravellers;}

    @Column(name="departure")
    private LocalDate departureDate;
    public LocalDate getDepartureDate() {return this.departureDate;}
    public void setDepartureDate(LocalDate departureDate)
        {this.departureDate = departureDate;}

    @Column(name="return")
    private LocalDate returnDate;
    public LocalDate getReturnDate() {return this.returnDate;}
    public void setReturnDate(LocalDate returnDate)
        {this.returnDate = returnDate;}

    @Column(name="fare")
    private Double fare;
    public Double getFare() {return this.fare;}
    public void setFare(Double fare) {this.fare = fare;}

    @Column(name="car_hire")
    private Double carHire;
    public Double getCarHire() {return this.carHire;}
    public void setCarHire(Double carHire) {this.carHire = carHire;}

    @Column(name="meals")
    private Double meals;
    public Double getMeals() {return this.meals;}
    public void setMeals(Double meals) {this.meals = meals;}

    @Column(name="accommodation")
    private Double accommodation;
    public Double getAccommodation() {return this.accommodation;}
    public void setAccommodation(Double accommodation)
        {this.accommodation = accommodation;}

    // TODO(Andrew): CostPerUnit
    // Return daily costs as costPerUnit
    // Pretty meaningless
    // Perhaps better to throw an exception
    @Override
    public Double getCostPerUnit()
    {
        return this.carHire + this.meals;
    }
    // CostPerUnit cannot be set explicitly for Travel expenses
    // TODO(Andrew): Warn user?
    @Override
    public void setCostPerUnit(Double cost) {}


    // =========================================================================
    // Constructors
    // =========================================================================

    public Travel() {super();}

    public Travel(  Project project,
                    ExpenseType type,
                    Double costPerUnit,
                    Double inKindPercent,
                    Integer numTravelers,
                    LocalDate departurDate,
                    LocalDate returnDate,
                    Double fare,
                    Double carHire,
                    Double meals,
                    Double accommodation)
    {
        super(project, type, costPerUnit, inKindPercent);

        this.setNumTravellers(numTravellers);
        this.setDepartureDate(departureDate);
        this.setReturnDate(returnDate);
        this.setFare(fare);
        this.setCarHire(carHire);
        this.setMeals(meals);
        this.setAccommodation(accommodation);
    }

    // =========================================================================
    // Methods
    // =========================================================================

    public Double travelDays()
    {
        this.annualExpenses
            .stream()
            .mapToDouble(AnnualExpense::getUnits)
            .reduce(0.0, (total, days) -> total + days);

        return null;
    }

    // Assumes there is only one annual expense for the given year,
    // which should be the case
    public double AnnualTravelDays(Year year)
    {
        return this.getAnnualExpenses()
            .stream()
            .filter(expense -> year.equals(expense.getID().getYear()))
            .mapToDouble(expense -> expense.getUnits())
            .findFirst()
            .orElse(0.0);
    }

    public double AnnualTravelNights(Year year)
    {
        // The final year of the expense
        Year finalYear = Year.of(
            this.getReturnDate().getYear()
        );

        // Subtract night of final day of travel 
        return this.AnnualTravelDays(year)
            - (year.equals(finalYear) ? 1.0 : 0.0);
    }
    
    private double AnnualTravelDays(AnnualExpense expense)
    {
        Double days = expense.getUnits();
        if (days == null) return 0.0;
        return (double) days;
    }

    // Returns 0 if any properties of Travel are null
    @Override
    public double AnnualCost(Year year)
    {
        if (this.getNumTravellers() == null
            || this.getCarHire() == null
            || this.getMeals() == null
            || this.getAccommodation() == null)
            return 0.0;

        return this.getNumTravellers() * (
            this.AnnualTravelDays(year) * (this.getCarHire() + this.getMeals())
            + this.AnnualTravelNights(year) * this.getAccommodation()
        );
    }


    private double AnnualTravelNights(AnnualExpense expense)
    {

        Year year = expense.getID().getYear();

        // The final year of the expense
        Year finalYear = Year.of(
            this.getReturnDate().getYear()
        );

        // Subtract night of final day of travel 
        return this.AnnualTravelDays(expense)
            - (year.equals(finalYear) ? 1 : 0);
    }


    @Override
    protected double AnnualCost(AnnualExpense expense)
    {
        if (this.getNumTravellers() == null
            || this.getCarHire() == null
            || this.getMeals() == null
            || this.getAccommodation() == null)
            return 0.0;

        return this.getNumTravellers() * (
            this.AnnualTravelDays(expense) * (this.getCarHire() + this.getMeals())
            + this.AnnualTravelNights(expense) * this.getAccommodation()
        );
    }

    @Override
    public double Cost()
    {
        return this.annualExpenses
            .stream()
            .mapToDouble(this::AnnualCost)
            .reduce(numTravellers * fare, (total, c) -> total + c);
    }
}