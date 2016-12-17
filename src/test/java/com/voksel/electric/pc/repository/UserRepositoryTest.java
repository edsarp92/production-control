package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.configuration.RepositoryConfiguration;
import com.voksel.electric.pc.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by edsarp on 8/14/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

    @Inject
    private UserRepository userRepository;

    @Test
    public void findByFormId() {
        User user = this.userRepository.findOneByUserName("admin");
        assertEquals(user.getUserId().toString(),"0");

    }
}
