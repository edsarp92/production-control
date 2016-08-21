package com.voksel.electric.pc.repository.search;

import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.repository.FormRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.junit.Assert.assertEquals;

/**
 * Created by edsarp on 8/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class FormSearchRepositoryTest {

    @Autowired
    private FormSearchRepository formSearchRepository;

    @Test
    public void findByFormId() {
        String expectedWord = "java";
        QueryBuilder matchQuery = QueryBuilders.matchQuery("formName", "Pendaftaran Form");
        List<Form>list= StreamSupport.stream(formSearchRepository.search(matchQuery).spliterator(), false)
                .collect(Collectors.toList());

    }
}

