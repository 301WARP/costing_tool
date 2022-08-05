package au.edu.utas.costing_tool.Mapper;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.DTO.CasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.NonCasualDetailsDTO;
import au.edu.utas.costing_tool.DTO.RHDDetailsDTO;

import au.edu.utas.costing_tool.Enums.CasualClassification;
import au.edu.utas.costing_tool.Enums.CasualStaffType;
import au.edu.utas.costing_tool.Enums.NonCasualClassification;
import au.edu.utas.costing_tool.Enums.NonCasualStaffType;
import au.edu.utas.costing_tool.Enums.PayCode;
import au.edu.utas.costing_tool.Enums.RHDIncomeStream;
import au.edu.utas.costing_tool.Enums.Step;

import au.edu.utas.costing_tool.Model.AnnualContribution;
import au.edu.utas.costing_tool.Model.Casual;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Model.Contribution;
import au.edu.utas.costing_tool.Model.NonCasual;
import au.edu.utas.costing_tool.Model.RHD;


public class ResearcherDetailsMapper extends ModelMapper
{
    // =========================================================================
    // Non-Casual converters
    // =========================================================================

    Converter<NonCasual, String> nonCasualStaffTypeConverter
        = new AbstractConverter<NonCasual, String>()
    {
        protected String convert(NonCasual contract)
        {
            if (contract == null) return null;

            NonCasualStaffType type = contract.getStaffType();

            if (type == null) return null;

            return type.name();
        }
    };

    Converter<NonCasual, String> nonCasualClassificationConverter
        = new AbstractConverter<NonCasual, String>()
    {
        protected String convert(NonCasual contract)
        {
            if (contract == null) return null;

            NonCasualClassification classification
                = contract.getClassification();

            if (classification == null) return null;

            return classification.name();
        }
    };

    Converter<NonCasual, Integer> nonCasualStepConverter
        = new AbstractConverter<NonCasual, Integer>()
    {
        protected Integer convert(NonCasual contract)
        {
            if (contract == null) return null;

            Step step = contract.getStep();

            if (step == null) return null;

            return step.ordinal();
        }
    };

    Converter<NonCasual, Double> nonCasualStartingSalaryConverter
        = new AbstractConverter<NonCasual, Double>()
    {
        protected Double convert(NonCasual contract)
        {
            if (contract == null) return null;

            Double salary = contract.getStartingSalary();

            if (salary == null) return null;

            return salary;
        }
    };

    Converter<NonCasual, Double> nonCasualWageAdjustmentConverter
        = new AbstractConverter<NonCasual, Double>()
    {
        protected Double convert(NonCasual contract)
        {
            if (contract == null) return null;

            Double salary = contract.getWageAdjustment();

            if (salary == null) return null;

            return salary;
        }
    };

    Converter<NonCasual, Double> nonCasualSalaryOnCostRateConverter
        = new AbstractConverter<NonCasual, Double>()
    {
        protected Double convert(NonCasual contract)
        {
            if (contract == null) return null;

            Double salary = contract.getSalaryOnCostRate();

            if (salary == null) return null;

            return salary;
        }
    };

    Converter<List<AnnualContribution>, Map<Integer, Double>>
    nonCasualAnnualContributionsConverter
        = new AbstractConverter<List<AnnualContribution>, Map<Integer, Double>>()
    {
        protected Map<Integer, Double> convert(List<AnnualContribution> contributions)
        {
            return contributions
                .stream()
                .collect(Collectors.toMap(  AnnualContribution::getYear,
                                            AnnualContribution::getFTE));
        }
    };


    // =========================================================================
    // Casual Converters
    // =========================================================================

    Converter<Casual, String> CasualStaffTypeConverter
        = new AbstractConverter<Casual, String>()
    {
        protected String convert(Casual contract)
        {
            if (contract == null) return null;

            CasualStaffType type = contract.getStaffType();

            if (type == null) return null;

            return type.name();
        }
    };

    Converter<Casual, String> CasualClassificationConverter
        = new AbstractConverter<Casual, String>()
    {
        protected String convert(Casual contract)
        {
            if (contract == null) return null;

            CasualClassification classification
                = contract.getClassification();

            if (classification == null) return null;

            return classification.name();
        }
    };

    Converter<Casual, Integer> CasualPayCodeConverter
        = new AbstractConverter<Casual, Integer>()
    {
        protected Integer convert(Casual contract)
        {
            if (contract == null) return null;

            PayCode payCode = contract.getPayCode();

            if (payCode == null) return null;

            return payCode.ordinal();
        }
    };

    Converter<Casual, Double> CasualHourlyRateConverter
        = new AbstractConverter<Casual, Double>()
    {
        protected Double convert(Casual contract)
        {
            if (contract == null) return null;

            Double hourlyRate = contract.getHourlyRate();

            if (hourlyRate == null) return null;

            return hourlyRate;
        }
    };

    Converter<Casual, Double> CasualWageAdjustmentConverter
        = new AbstractConverter<Casual, Double>()
    {
        protected Double convert(Casual contract)
        {
            if (contract == null) return null;

            Double salary = contract.getWageAdjustment();

            if (salary == null) return null;

            return salary;
        }
    };

    Converter<Casual, Double> CasualSalaryOnCostRateConverter
        = new AbstractConverter<Casual, Double>()
    {
        protected Double convert(Casual contract)
        {
            if (contract == null) return null;

            Double salary = contract.getSalaryOnCostRate();

            if (salary == null) return null;

            return salary;
        }
    };

    Converter<List<AnnualContribution>, Map<Integer, Double>>
    CasualAnnualContributionsConverter
        = new AbstractConverter<List<AnnualContribution>, Map<Integer, Double>>()
    {
        protected Map<Integer, Double> convert(List<AnnualContribution> contributions)
        {
            return contributions
                .stream()
                .collect(Collectors.toMap(  AnnualContribution::getYear,
                                            AnnualContribution::getHours));
        }
    };
    

    // =========================================================================
    // RHD Converters
    // =========================================================================

    Converter<RHD, String> RHDIncomeStreamConverter
        = new AbstractConverter<RHD, String>()
    {
        protected String convert(RHD contract)
        {
            if (contract == null) return null;

            RHDIncomeStream classification = contract.getClassification();

            if (classification == null) return null;

            return classification.name();
        }
    };

    Converter<RHD, Double> RHDWageExpenseConverter
        = new AbstractConverter<RHD, Double>()
    {
        protected Double convert(RHD contract)
        {
            if (contract == null) return null;

            Double wageExpense = contract.getAnnualSalary();

            if (wageExpense == null) return null;

            return wageExpense;
        }
    };

    Converter<List<AnnualContribution>, Map<Integer, Double>>
    RHDAnnualContributionsConverter
        = new AbstractConverter<List<AnnualContribution>, Map<Integer, Double>>()
    {
        protected Map<Integer, Double> convert(List<AnnualContribution> contributions)
        {
            return contributions
                .stream()
                .collect(Collectors.toMap(  AnnualContribution::getYear,
                                            AnnualContribution::getFTE));
        }
    };


    // =========================================================================
    // Constructors
    // =========================================================================

    public ResearcherDetailsMapper()
    {
        super();

        this.initNonCasualDetailsDTOMappings();
        this.initCasualDetailsDTOMappings();
        this.initRHDDetailsDTOMappings();
    }

    interface Test {
        public abstract Enum<?> t();
    }

    private interface Foo {
        abstract Contract f();
    }

    private Converter<Contract, String>
    contractConverter(Method m)
    {
        return new AbstractConverter<Contract, String>() {

            @Override
            protected String convert(Contract c)
            {
                if (c == null) return null;

                Object o = null;
                Enum<?> e = null;

                try {
                    o = m.invoke(c);
                } catch (InvocationTargetException x) {
                    System.err.format("Invocation of %s failed: %s\n",
                                      m.getName(), x.getCause());

                } catch (IllegalAccessException x) {
                    System.err.format("Illegal access of %s by %s: %s\n",
                                      c.getClass(), m.getName(), x.getCause());
                }

                if (!(o instanceof Enum<?>)) return null;

                e = (Enum<?>)o;
                if (e == null) return null;

                return e.name();
            }
        };
    }
    

    // =========================================================================
    // Methods
    // =========================================================================

    public void initNonCasualDetailsDTOMappings()
    {
        this.typeMap(Contribution.class, NonCasualDetailsDTO.class)
            .addMappings(mapper ->
        {
            //mapper  .using(this.nonCasualStaffTypeConverter)
            try {
            mapper  .using(this.contractConverter(NonCasual.class.getMethod("getStaffType")))
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setStaffType);
            } catch (NoSuchMethodException x)
            {
                System.err.format("Class %s has no method %s: %s\n",
                                NonCasual.class, "getStaffType", x.getCause());
            }

            mapper  .using(this.nonCasualClassificationConverter)
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setClassification);

            mapper  .using(this.nonCasualStepConverter)
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setStep);

            mapper  .using(this.nonCasualStartingSalaryConverter)
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setStartingSalary);

            mapper  .using(this.nonCasualWageAdjustmentConverter)
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setWageAdjustment);

            mapper  .using(this.nonCasualSalaryOnCostRateConverter)
                    .map(   Contribution::getContract,
                            NonCasualDetailsDTO::setSalaryOnCostRate);

            mapper  .using(this.nonCasualAnnualContributionsConverter)
                    .map(   Contribution::getAnnualContributions,
                            NonCasualDetailsDTO::setAnnualContributions);
        });
    }

    public void initCasualDetailsDTOMappings()
    {
        this.typeMap(Contribution.class, CasualDetailsDTO.class)
            .addMappings(mapper ->
        {
            mapper  .using(this.CasualStaffTypeConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setStaffType);

            mapper  .using(this.CasualClassificationConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setClassification);

            mapper  .using(this.CasualPayCodeConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setPayCode);

            mapper  .using(this.CasualHourlyRateConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setHourlyRate);

            mapper  .using(this.CasualWageAdjustmentConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setWageAdjustment);

            mapper  .using(this.CasualSalaryOnCostRateConverter)
                    .map(   Contribution::getContract,
                            CasualDetailsDTO::setSalaryOnCostRate);

            mapper  .using(this.CasualAnnualContributionsConverter)
                    .map(   Contribution::getAnnualContributions,
                            CasualDetailsDTO::setAnnualContributions);
        });
    }

    public void initRHDDetailsDTOMappings()
    {
        this.typeMap(Contribution.class, RHDDetailsDTO.class)
            .addMappings(mapper ->
        {
            mapper  .using(this.RHDIncomeStreamConverter)
                    .map(   Contribution::getContract,
                            RHDDetailsDTO::setClassification);

            mapper  .using(this.RHDWageExpenseConverter)
                    .map(   Contribution::getContract,
                            RHDDetailsDTO::setWageExpense);

            mapper  .using(this.RHDAnnualContributionsConverter)
                    .map(   Contribution::getAnnualContributions,
                            RHDDetailsDTO::setAnnualContributions);
        });
    }
}