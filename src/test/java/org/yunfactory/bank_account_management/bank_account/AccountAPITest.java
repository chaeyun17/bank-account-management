package org.yunfactory.bank_account_management.bank_account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class AccountAPITest {

  @Autowired
  MockMvc mvc;

  @Test
  public void getAll(){
    
  }
  
}