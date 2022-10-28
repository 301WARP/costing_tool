package au.edu.utas.costing_tool.DTO.Expense;


// =============================================================================
// External Imports
// =============================================================================

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;


@Configuration
public class ExpenseDetailsDeserializerConfiguration
{
    @Bean
    public Module deserializerModule()
    {
        SimpleModule module = new SimpleModule();
        module.addDeserializer( ExpenseDetailsDTO.class,
                                new ExpenseDetailsDeserializer());
        return module;
    }
}
