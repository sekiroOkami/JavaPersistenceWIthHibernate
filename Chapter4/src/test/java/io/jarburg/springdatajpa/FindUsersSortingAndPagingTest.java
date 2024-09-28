package io.jarburg.springdatajpa;

import io.jarburg.springdatajpa.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class FindUsersSortingAndPagingTest extends SpringDataJpaApplicationTests {

    @Test
    void testOrder() {
        var u1 = userRepository.findFirstByOrderByUsernameAsc().orElseThrow();
        var u2 = userRepository.findTopByOrderByRegistrationDateDesc().orElseThrow();
        Page<User> userPage = userRepository.findAll(PageRequest.of(1,3));
        List<User> users = userRepository.findFirst2ByLevel(2, Sort.by("registrationDate"));

        assertAll(
                ()-> assertThat("Marika").isEqualTo(u1.getUsername()),
                ()-> assertThat("Marika").isEqualTo(u2.getUsername()),
                ()-> assertThat(2).isEqualTo(users.size()),
                ()-> assertThat(3).isEqualTo(userPage.getSize()),
                ()-> assertThat("Radagon").isEqualTo(users.get(0).getUsername()),
                ()-> assertThat("Rennala").isEqualTo(users.get(1).getUsername())
        );
    }
}
