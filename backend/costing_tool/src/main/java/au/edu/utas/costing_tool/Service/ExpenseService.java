package au.edu.utas.costing_tool.Service;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

// =============================================================================
// External Imports
// =============================================================================

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.utas.costing_tool.DTO.Contribution.ContributionDetailsDTO;
import au.edu.utas.costing_tool.DTO.Researcher.ResearcherRecommendationDTO;
import au.edu.utas.costing_tool.Enums.Expense.ExpenseType;
import au.edu.utas.costing_tool.Enums.Researcher.Title;
import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Contribution.AnnualContribution;
import au.edu.utas.costing_tool.Model.Contribution.Contribution;
import au.edu.utas.costing_tool.Model.Contribution.ContributionID;
import au.edu.utas.costing_tool.Model.Expense.EquipmentPurchases;
import au.edu.utas.costing_tool.Model.Expense.Expense;
import au.edu.utas.costing_tool.Model.Project.Project;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;
import au.edu.utas.costing_tool.Repository.AnnualContributionRepository;
import au.edu.utas.costing_tool.Repository.ContributionRepository;
import au.edu.utas.costing_tool.Repository.ExpenseRepository;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;
import au.edu.utas.costing_tool.Util.Log;


@Service
@Data
public class ExpenseService
{
    // =========================================================================
    // Properties
    // =========================================================================

    @Autowired
    private final ExpenseRepository xRepos;

    @Autowired
    private final ResearcherDetailsMapper detailsMapper;

    private static final Pageable page = PageRequest.of(0, 10);


    // =========================================================================
    // Methods
    // =========================================================================

    public List<Expense> listAllExpensesForProject(Long projectID)
    {
        return xRepos.findAllByProject(projectID);
    }

    public Expense findExpense(Long id)
    {
        return xRepos.findById(id).orElse(null);
    }

    /*
    @Transactional
    public
    Expense
    createExpense(Project project, Class<? extends Expense> c)
    {
        Log.log("NOT GENERIC");
        Expense test = xRepos.findById(1L).orElse(null);

        Log.log(test);

        Expense expense = c.cast(Expense.builder()
            //.project(project)
            .type(ExpenseType.NONE)
            .build());

        Log.log(expense);

        return xRepos.save(expense);
    }
    */

    @Transactional
    public
    <T extends Expense>
    Expense
    createExpense(Expense expense, Class<T> clazz)
    {
        Expense x;
        try {
            x = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        x = xRepos.save(x);

        expense.setId(x.getId());

        return xRepos.save(expense);
    }

    /*
    @Transactional
    public Contribution updateContribution(Contribution old, Contribution nw)
    {
        if (old == null || nw == null)
            return null;

        old.setInKindPercent(nw.getInKindPercent());
        old.setRole(nw.getRole());
        old.setWageAdjustment(nw.getWageAdjustment());
        old.setOnCostRate(nw.getOnCostRate());

        // Update annual contributions
        this.remove(old, nw);
        this.add(old, nw);
        this.update(old, nw);

        return old;
    }

    @Transactional
    private void remove(Contribution old, Contribution nw)
    {
        List<AnnualContribution> toRemove
            = old   .getAnnualContributions()
                    .stream()
                    .filter(a -> !nw.hasAnnualContribution(a.getYear()))
                    .collect(Collectors.toList());

        toRemove.stream().forEach(a -> old.removeAnnualContribution(a));
        acRepos.deleteAllInBatch(toRemove);
    }

    @Transactional
    private void add(Contribution old, Contribution nw)
    {
        List<AnnualContribution> toAdd
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> !old.hasAnnualContribution(a.getYear()))
                .collect(Collectors.toList());

        toAdd.stream().forEach(a -> old.addAnnualContribution(a));
    }

    @Transactional
    private void update(Contribution old, Contribution nw)
    {
        Map<AnnualContribution, AnnualContribution> toUpdate
            = nw.getAnnualContributions()
                .stream()
                .filter(a -> old.hasAnnualContribution(a.getYear()))
                .collect(Collectors.toMap(
                    Function.identity(),
                    a -> old.findAnnualContributionByYear(a.getYear())
                ));
        
        toUpdate.entrySet().forEach(e -> {
            AnnualContribution k = e.getKey();
            AnnualContribution v = e.getValue();
            if (v != null)
                v.setUnits(k.getUnits());
        });
    }

    @Transactional
    public
    void
    deleteContribution(Contribution contribution)
    {
        cRepos.delete(contribution);
    }

    @Transactional
    public
    List<ResearcherRecommendationDTO>
    recommendResearchers(ResearcherRecommendationDTO dto)
    {
        List<Researcher> researchers;

        String fName = dto.getFirstName();
        String lName = dto.getLastName();
        Title title;

        String maybeTitle = dto.getTitle();

        if (maybeTitle == null) {
            title = null;
        }
        else {
            try {
                title = Title.valueOf(dto.getTitle());
            } catch (NullPointerException e) {
                title = null;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        boolean titleAbsent = title == null || title == Title.NONE;
        boolean nameAbsent = fName == null && lName == null;

        if (titleAbsent && nameAbsent)
            researchers = rRepos.findAll(page).getContent();
        else if (titleAbsent)
            researchers = rRepos.recommend(fName, lName, page);
        else
            researchers = rRepos.recommend(title, fName, lName, page);
        
        return researchers
            .stream()
            .map(r -> ResearcherRecommendationDTO.builder()
                        .staffID(r.getStaffID())
                        .title(r.getTitle().toString())
                        .firstName(r.getFirstName())
                        .lastName(r.getLastName())
                        .build())
            .collect(Collectors.toList());
    }


    @Transactional
    public
    List<ResearcherRecommendationDTO>
    recommendResearchers(Title title, String firstName, String lastName)
    {
        List<Researcher> researchers;

        if (title.equals(Title.NONE) || title.equals(Title.TBA))
            researchers = this.rRepos.recommend( firstName, lastName, page);
        else
            researchers = this.rRepos.recommend( title, firstName, lastName, page);
        
        return researchers
            .stream()
            .map(r -> ResearcherRecommendationDTO.builder()
                        .staffID(r.getStaffID())
                        .title(r.getTitle().toString())
                        .firstName(r.getFirstName())
                        .lastName(r.getLastName())
                        .build())
            .collect(Collectors.toList());
    }
    */

}