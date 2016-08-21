package com.voksel.electric.pc.repository.search;

import com.voksel.electric.pc.domain.entity.Form;
import com.voksel.electric.pc.domain.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by edsarp on 8/21/16.
 */
public interface FormSearchRepository extends ElasticsearchRepository<Form,String> {
}
