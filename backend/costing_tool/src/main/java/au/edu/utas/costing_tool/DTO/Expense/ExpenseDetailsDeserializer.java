package au.edu.utas.costing_tool.DTO.Expense;


// =============================================================================
// External Imports
// =============================================================================

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;

// =============================================================================
// Project Imports
// =============================================================================

import au.edu.utas.costing_tool.Enums.Expense.ExpenseType;


public class ExpenseDetailsDeserializer extends StdDeserializer<ExpenseDetailsDTO>
{ 
    public ExpenseDetailsDeserializer() { 
        this(null); 
    } 

    public ExpenseDetailsDeserializer(Class<?> vc) { 
        super(vc); 
    }

    private String getString(JsonNode node, String s) throws Exception 
    {
        JsonNode strNode = node.get(s);

        if (strNode instanceof MissingNode)
            throw new Exception("Missing node: " + s);

        if (strNode instanceof NullNode)
            return null;

        return strNode.asText();
    }

    private Integer getInt(JsonNode node, String s) throws Exception
    {
        JsonNode intNode = node.get(s);

        if (intNode instanceof MissingNode)
            throw new Exception("Missing node: " + s);

        if (intNode instanceof NullNode)
            return null;

        return (Integer) ((IntNode)intNode).numberValue();
    }

    private Long getLong(JsonNode node, String s) throws Exception
    {
        JsonNode longNode = node.get(s);

        if (longNode instanceof MissingNode)
            throw new Exception("Missing node: " + s);

        if (longNode instanceof NullNode)
            return null;

        return (Long) ((LongNode)longNode).numberValue();
    }

    private Double getDouble(JsonNode node, String s) throws Exception
    {
        JsonNode dblNode = node.get(s);

        if (dblNode instanceof MissingNode)
            throw new Exception("Missing node: " + s);

        if (dblNode instanceof NullNode)
            return null;

        return (Double) ((DoubleNode)dblNode).numberValue();
    }

    private
    Map<Integer, Double>
    getAnnualQuantity(JsonNode node) throws Exception
    {
        JsonNode aqNode = node.get("annualQuantity");

        if (aqNode instanceof MissingNode)
            throw new Exception("Missing node: annualQuantity");

        if (aqNode instanceof NullNode)
            return null;

        ObjectMapper mapper = new ObjectMapper();

        return mapper.convertValue(
            aqNode,
            new TypeReference<Map<Integer, Double>>(){}
        );
    }

    @Override
    public
    ExpenseDetailsDTO
    deserialize(JsonParser jp, DeserializationContext ctxt) 
    throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);

        try {
            switch (getString(node, "type")) {
                case "TRAVEL":
                    return travel(node);
                case "LABORATORY_HIRE":
                    return laboratoryHire(node);
                case "CONSUMABLES":
                    return consumables(node);
                case "EQUIPMENT_PURCHASES":
                    return equipmentPurchase(node);
                case "EXTERNAL_CONTRACTOR":
                    return externalContractor(node);
                case "OTHER_COSTS":
                    return otherCosts(node);
                case "AUDIT_FEES":
                    return auditFees(node);
                case "RHD_NON_STIPEND_COSTS":
                    return rhdNonStipendCosts(node);
                case "FACILITY_COSTS":
                    return facilityCosts(node);
                case "PARTNER_ORGANISATIONS":
                    return partnerOrganisations(node);
                default:
                    throw new JsonMappingException(jp, "Unknown expense type");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private
    TravelDetailsDTO
    travel(JsonNode node)
    {
        try {
            return TravelDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.TRAVEL.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .numTravellers(getInt(node, "numTravellers"))
                .departureDate(getString(node, "departureDate"))
                .returnDate(getString(node, "returnDate"))
                .accommodation(getDouble(node, "accommodation"))
                .carHire(getDouble(node, "carHire"))
                .meals(getDouble(node, "meals"))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    LaboratoryHireDetailsDTO
    laboratoryHire(JsonNode node)
    {
        try {
            return LaboratoryHireDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.LABORATORY_HIRE.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    ConsumableDetailsDTO
    consumables( JsonNode node)
    {
        try {
            return ConsumableDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.CONSUMABLES.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    EquipmentPurchaseDetailsDTO
    equipmentPurchase(JsonNode node)
    {
        try {
            return EquipmentPurchaseDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.EQUIPMENT_PURCHASES.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    ExternalContractorDetailsDTO
    externalContractor(JsonNode node)
    {
        try {
            return ExternalContractorDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.EXTERNAL_CONTRACTOR.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    OtherCostsDetailsDTO
    otherCosts(JsonNode node)
    {
        try {
            return OtherCostsDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.OTHER_COSTS.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    AuditFeeDetailsDTO
    auditFees(JsonNode node)
    {
        try {
            return AuditFeeDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.AUDIT_FEES.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .auditCostName(getString(node, "auditCostName"))
                .units(getString(node, "units"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    RhdNonStipendCostDetailsDTO
    rhdNonStipendCosts(JsonNode node)
    {
        try {
            return RhdNonStipendCostDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.RHD_NON_STIPEND_COSTS.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    FacilityCostDetailsDTO
    facilityCosts(JsonNode node)
    {
        try {
            return FacilityCostDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.FACILITY_COSTS.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .facilityCostName(getString(node, "facilityCostName"))
                .units(getString(node, "units"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private
    PartnerOrganisationDetailsDTO
    partnerOrganisations(JsonNode node)
    {
        try {
            return PartnerOrganisationDetailsDTO.builder()
                .id(getLong(node, "id"))
                .type(ExpenseType.PARTNER_ORGANISATION.toString())
                .description(getString(node, "description"))
                .inKindPercent(getDouble(node, "inKindPercent"))
                .costPerUnit(getDouble(node, "costPerUnit"))
                .organisation(getString(node, "organisation"))
                .annualQuantity(getAnnualQuantity(node))
                .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}