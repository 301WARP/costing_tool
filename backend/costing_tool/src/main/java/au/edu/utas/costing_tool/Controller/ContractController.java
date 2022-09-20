package au.edu.utas.costing_tool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import au.edu.utas.costing_tool.DTO.ContractDTO;
import au.edu.utas.costing_tool.Model.Contract;
import au.edu.utas.costing_tool.Repository.ContractRepository;
import au.edu.utas.costing_tool.Service.ContractService;
import au.edu.utas.costing_tool.Service.ContributionService;
import lombok.Data;


@Data
@RestController
public class ContractController
{
    @Autowired
    private final ContractService contractService;

    @Autowired
    private final ContributionService contributionService;

    @Autowired
    private final ContractMapper contractMapper;
    
    @GetMapping(path="/contracts/{staffID}")
    public 
    ResponseEntity<List<ContractDTO>>
    fetchContractsforUser(@PathVariable Long staffID)
    {
        if (staffID == null)
            return ResponseEntity.badRequest().build();
        
        Researcher researcher = this.contributionService.findRes(staffID);
        
        List<Contract> contracts =
            this.contractService.fetchContractsFor(staffID);
        
        if (contracts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(this.contractMapper.map(contracts));
    }
}

