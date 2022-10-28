package au.edu.utas.costing_tool.costing_tool.Repository;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import au.edu.utas.costing_tool.Enums.Researcher.Title;
import au.edu.utas.costing_tool.Model.Contract.Contract;
import au.edu.utas.costing_tool.Model.Researcher.Researcher;
import au.edu.utas.costing_tool.Repository.ResearcherRepository;

@DataJpaTest
//@TestPropertySource(properties="spring.jpa.hibernate.ddl-auto=validate")
public class ResearcherRepositoryTest
{
    @Autowired
    private ResearcherRepository researcherRepos;

    private static final Pageable page = PageRequest.of(0, 10);

    @Test
    @Sql(scripts="classpath:database/Repository/ResearcherRepository/recommend.sql")
    public void recommend_test()
    {
        Researcher firstLast = Researcher.builder()
            .staffID(1L)
            .title(Title.DR)
            .firstName("First")
            .lastName("Last")
            .contracts(new ArrayList<Contract>())
            .build();
        Researcher mrAnotherLast = Researcher.builder()
            .staffID(4L)
            .title(Title.MR)
            .firstName("Another")
            .lastName("Last")
            .contracts(new ArrayList<Contract>())
            .build();
        Researcher msAnotherLast = Researcher.builder()
            .staffID(5L)
            .title(Title.MS)
            .firstName("Another")
            .lastName("Last")
            .contracts(new ArrayList<Contract>())
            .build();
        Researcher firstAlso = Researcher.builder()
            .staffID(6L)
            .title(Title.MR)
            .firstName("First")
            .lastName("Also")
            .contracts(new ArrayList<Contract>())
            .build();
        
        List<Researcher> expected = List.of(
            firstLast,
            mrAnotherLast,
            msAnotherLast,
            firstAlso
        );

        List<Researcher> result =
            researcherRepos.recommend("First", "Last", page);

        Assertions
            .assertThat(result)
            .usingRecursiveComparison()
            .isEqualTo(expected);
    }
}