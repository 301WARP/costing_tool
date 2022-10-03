package au.edu.utas.costing_tool.costing_tool;


// =============================================================================
// External Imports
// =============================================================================

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Controller.AnnualContributionController;
import au.edu.utas.costing_tool.Controller.ClearanceController;
import au.edu.utas.costing_tool.Controller.ContractController;
import au.edu.utas.costing_tool.Controller.ExpenseController;
import au.edu.utas.costing_tool.Controller.ProjectController;
import au.edu.utas.costing_tool.Controller.ResearcherController;
import au.edu.utas.costing_tool.Mapper.ClearanceMapper;
import au.edu.utas.costing_tool.Mapper.ContractMapper;
import au.edu.utas.costing_tool.Mapper.ProjectDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ProjectListMapper;
import au.edu.utas.costing_tool.Mapper.ResearcherDetailsMapper;
import au.edu.utas.costing_tool.Mapper.ResearcherListMapper;
import au.edu.utas.costing_tool.Mapper.UpdateContributionMapper;
import au.edu.utas.costing_tool.Repository.AnnualContributionRepository;
import au.edu.utas.costing_tool.Repository.ContractRepository;
import au.edu.utas.costing_tool.Repository.ContributionRepository;
import au.edu.utas.costing_tool.Repository.ExpenseRepository;
import au.edu.utas.costing_tool.Repository.ProjectRepository;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;



@SpringBootTest
class CostingToolApplicationTests
{
	// =========================================================================
	// Controllers
	// =========================================================================

	@Autowired
	private AnnualContributionController acController;

	@Autowired
	private ClearanceController clearanceController;

	@Autowired
	private ContractController contractController;

	@Autowired
	private ExpenseController expenseController;

	@Autowired
	private ProjectController projectController;

	@Autowired
	private ResearcherController researcherController;


	// =========================================================================
	// Mappers
	// =========================================================================

	@Autowired
	private ClearanceMapper clearanceMapper;

	@Autowired
	private ContractMapper contractMapper;

	@Autowired
	private ProjectDetailsMapper projectDetailsMapper;

	@Autowired
	private ProjectListMapper projectListMapper;

	@Autowired
	private ResearcherDetailsMapper researcherDetailsMapper;

	@Autowired
	private ResearcherListMapper researcherListMapper;

	@Autowired
	private UpdateContributionMapper updateContributionMapper;


	// =========================================================================
	// Repositories
	// =========================================================================

	@Autowired
	private AnnualContributionRepository acRepos;

	@Autowired
	private ContractRepository contractRepos;

	@Autowired
	private ContributionRepository contributionRepos;

	@Autowired
	private ExpenseRepository expenseRepos;

	@Autowired
	private ProjectRepository projectRepos;

	@Autowired
	private ResearcherRepository researcherRepos;


	@Test
	void contextLoads()
	{
		// Controllers
		assertThat(acController).isNotNull();
		assertThat(clearanceController).isNotNull();
		assertThat(contractController).isNotNull();
		assertThat(expenseController).isNotNull();
		assertThat(projectController).isNotNull();
		assertThat(researcherController).isNotNull();

		// Mappers
		assertThat(clearanceMapper).isNotNull();
		assertThat(contractMapper).isNotNull();
		assertThat(projectDetailsMapper).isNotNull();
		assertThat(projectListMapper).isNotNull();
		assertThat(researcherDetailsMapper).isNotNull();
		assertThat(researcherListMapper).isNotNull();
		assertThat(updateContributionMapper).isNotNull();

		// Repositories
		assertThat(acRepos).isNotNull();
		assertThat(contractRepos).isNotNull();
		assertThat(contributionRepos).isNotNull();
		assertThat(expenseRepos).isNotNull();
		assertThat(projectRepos).isNotNull();
		assertThat(researcherRepos).isNotNull();
	}

}