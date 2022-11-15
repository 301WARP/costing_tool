package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract.CasualPaymentDetails;
import au.edu.utas.costing_tool.Model.Contract.CasualPaymentDetailsID;


@Repository
public interface CasualPaymentDetailsRepository
    extends JpaRepository<CasualPaymentDetails, CasualPaymentDetailsID>
{
    
}