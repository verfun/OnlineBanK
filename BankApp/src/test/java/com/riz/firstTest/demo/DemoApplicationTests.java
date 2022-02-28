package com.riz.firstTest.demo;

import com.riz.firstTest.demo.Models.Account;
import com.riz.firstTest.demo.Repositories.AccountRepository;
import com.riz.firstTest.demo.Repositories.TransactionRepository;
import com.riz.firstTest.demo.Services.AccountService;
import com.riz.firstTest.demo.Services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoApplicationTests {

}
