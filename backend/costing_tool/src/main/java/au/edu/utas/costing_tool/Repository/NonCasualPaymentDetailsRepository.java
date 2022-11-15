package au.edu.utas.costing_tool.Repository;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Model.Contract.NonCasualPaymentDetails;
import au.edu.utas.costing_tool.Model.Contract.NonCasualPaymentDetailsID;


@Repository
public interface NonCasualPaymentDetailsRepository
    extends JpaRepository<NonCasualPaymentDetails, NonCasualPaymentDetailsID>
{
}
