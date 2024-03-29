package br.com.playground.service;

import br.com.playground.model.User;
import br.com.playground.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void calculate_salary_until_2000_tax_should_be_two_percent() {
        Mockito.when(userRepository.findBy(Mockito.any())).thenReturn(buildUser(1990d));
        double calculated = userService.calculate(UUID.randomUUID());
        Assertions.assertEquals(1950.20, calculated);
    }

    @Test
    void calculate_salary_greather_than_2000_tax_should_be_three_percent() {
        Mockito.when(userRepository.findBy(Mockito.any())).thenReturn(buildUser(2500d));
        double calculated = userService.calculate(UUID.randomUUID());
        Assertions.assertEquals(2425d, calculated);
    }

    @Test
    void calculate_salary_greather_than_4000_tax_should_be_four_percent() {
        Mockito.when(userRepository.findBy(Mockito.any())).thenReturn(buildUser(4001d));
        double calculated = userService.calculate(UUID.randomUUID());
        Assertions.assertEquals(3840.96, calculated);
    }

    private User buildUser(Double salary) {
        var uuid = UUID.randomUUID();
        var user = new User("John", salary);
        user.setUuid(uuid);
        return user;
    }
}
