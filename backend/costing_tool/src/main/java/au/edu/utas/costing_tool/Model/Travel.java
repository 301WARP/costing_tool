package au.edu.utas.costing_tool.Model;


// =============================================================================
// External Imports
// =============================================================================

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("TRAVEL")
public class Travel extends Expense
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Column(name="num_travellers")
    private Integer numTravellers;

    @Column(name="departure")
    private LocalDate departureDate;

    @Column(name="return")
    private LocalDate returnDate;

    @Column(name="fare")
    private Double fare;

    @Column(name="car_hire")
    private Double carHire;

    @Column(name="meals")
    private Double meals;

    @Column(name="accommodation")
    private Double accommodation;

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


    /*
    // =========================================================================
    // Constructors
    // =========================================================================

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
    */

    // =========================================================================
    // Methods
    // =========================================================================

    public Double travelDays()
    {
        LocalDate d = this.getDepartureDate();
        LocalDate r = this.getReturnDate();

        if (d == null || r == null)
            return null;

        // Include both the first and last days of travel
        return Double.valueOf(ChronoUnit.DAYS.between(d, r)) + 1;
    }

    // Assumes there is only one annual expense for the given year,
    // which should be the case
    public Double AnnualTravelDays(Integer year)
    {
        return this.getAnnualExpenses()
            .stream()
            .filter(expense -> year.equals(expense.getYear()))
            .mapToDouble(expense -> expense.getUnits())
            .findFirst()
            .orElse(0.0);
    }

    public double AnnualTravelNights(Integer year)
    {
        // The final year of the expense
        Year finalYear = Year.of(
            this.getReturnDate().getYear()
        );

        // Subtract night of final day of travel 
        return this.AnnualTravelDays(year)
            - (year.equals(finalYear) ? 1.0 : 0.0);
    }
    
    private Double AnnualTravelDays(AnnualExpense expense)
    {
        Double days = expense.getUnits();

        if (days == null)
            return null;

        return days;
    }

    // Returns 0 if any properties of Travel are null
    @Override
    public Double AnnualCost(Integer year)
    {
        Integer travellers = this.getNumTravellers();
        Double carHire = this.getCarHire();
        Double meals = this.getMeals();
        Double accommodation = this.getAccommodation();

        Double days = this.AnnualTravelDays(year);
        Double nights = this.AnnualTravelDays(year);

        if (travellers == null
            || carHire == null
            || meals == null
            || accommodation == null
            || days == null
            || nights == null)
            return null;

        return travellers * (
            days * (carHire + meals)
            + nights * accommodation
        );
    }

    public Double AnnualTravelNights(AnnualExpense expense)
    {
        if (expense == null)
            return null;

        Integer year = expense.getYear();
        LocalDate rDate = this.getReturnDate();
        Double days = this.AnnualTravelDays(expense);

        if (year == null || rDate == null || days == null)
            return null;

        // The final year of the expense
        Integer finalYear = rDate.getYear();

        // Subtract night of final day of travel 
        return days - (year.equals(finalYear) ? 1.0 : 0.0);
    }

    @Override
    public Double AnnualCost(AnnualExpense expense)
    {
        if (expense == null)
            return null;
        
        Integer travellers = this.getNumTravellers();
        Double carHire = this.getCarHire();
        Double meals = this.getMeals();
        Double accommodation = this.getAccommodation();

        Double days = this.AnnualTravelDays(expense);
        Double nights = this.AnnualTravelDays(expense);

        if (travellers == null
            || carHire == null
            || meals == null
            || accommodation == null)
            return null;

        return travellers * (
            days * (carHire + meals)
            + nights * accommodation
        );
    }

    @Override
    public Double Cost()
    {
        // TODO(Andrew): A version like thisis useful if expenses change per
        //               year, e.g. inflation
        /*
        return this.getAnnualExpenses()
            .stream()
            .mapToDouble(this::AnnualCost)
            .reduce(numTravellers * fare, (total, c) -> total + c);
        */

        LocalDate dDate = this.getDepartureDate();
        LocalDate rDate = this.getReturnDate();

        if (dDate == null || rDate == null)
            return null;

        Double nights = Double.valueOf(ChronoUnit.DAYS.between(dDate, rDate));
        Double days = nights + 1;

        Integer travellers = this.getNumTravellers();
        Double carHire = this.getCarHire();
        Double meals = this.getMeals();
        Double accommodation = this.getAccommodation();

        if (travellers == null
            || carHire == null
            || meals == null
            || accommodation == null)
            return null;

        return travellers * (
            days * (carHire + meals)
            + nights * accommodation
        );
    }
}