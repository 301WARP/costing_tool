package au.edu.utas.costing_tool.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import au.edu.utas.costing_tool.DTO.Contract.ContractDTO;
import au.edu.utas.costing_tool.Mapper.ContractMapper;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Service.ContractService;
import au.edu.utas.costing_tool.Service.ResearcherService;
import lombok.Data;


@Data
@RestController
public class ContractController
{
    @Autowired
    private final ContractService contractService;

    @Autowired
    private final ResearcherService researcherService;

    @Autowired
    private final ContractMapper contractMapper;
    
    @CrossOrigin(origins="*")
    @GetMapping(path="/contracts/{staffID}")
    public 
    ResponseEntity<List<ContractDTO>>
    fetchContractsforResearcher(@PathVariable Long staffID)
    {
        if (staffID == null)
            return ResponseEntity.badRequest().build();
        
        if (!this.researcherService.existsById(staffID))
            return ResponseEntity.notFound().build();
        
        List<Contract> contracts =
            this.contractService.findContractsFor(staffID);
        
        if (contracts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(this.contractMapper.map(contracts));
    }

    /*
    @CrossOrigin(origins="*")
    @GetMapping(path="/contracts/details/{contractID}")
    public 
    ResponseEntity<List<ContractDTO>>
    fetchContractDetails(@PathVariable Long contractID)
    {
        if (contractID == null)
            return ResponseEntity.badRequest().build();
        
        if (!this.researcherService.existsById(contractID))
            return ResponseEntity.notFound().build();
        
        List<Contract> contracts =
            this.contractService.findContractsFor(contractID);
        
        if (contracts.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(this.contractMapper.map(contracts));
    }
    */
}

