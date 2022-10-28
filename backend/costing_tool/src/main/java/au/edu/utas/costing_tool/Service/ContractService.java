package au.edu.utas.costing_tool.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Repository.ContractRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService
{
    @Autowired
    private final ContractRepository contractRepos;

    public Contract findContract(Long id)
    {
        if (id == null)
            return null;

        return this.contractRepos.findById(id).orElse(null);
    }

    public boolean exists(Long id)
    {
        if (id == null)
            return false;
        
        return this.contractRepos.existsById(id);
    }

    public List<Contract> findContractsFor(Long staffID)
    {
        if (staffID == null)
            return null;
        
        return this.contractRepos.findForResearcher(staffID);
    }
}