package org.yunfactory.bank_account_management.bank_account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
@ActiveProfiles("test")
public class AccountIntegrationTest {   

    @Autowired
    private MockMvc mockMvc;
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @Sql(scripts={"classpath:sql/test-data.sql"})
    @DisplayName("Account 변경사항 업데이트 시, 변경된 Account 반환")
    public void Given_Changes_When_Update_Then_Returned_Account() throws Exception{

        // given
        AccountCreationDto creationDto = new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 123L);
        String requestBody = mapper.writeValueAsString(creationDto);

        // when
        ResultActions result = mockMvc.perform(put("/api/accounts/{id}", 1L)
                                                .header("content-type", "application/json; charset=utf-8")
                                                .content(requestBody));
        // then
        result.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.purpose").value(creationDto.getPurpose()))
                .andExpect(jsonPath("$.bankName").value(creationDto.getBankName()))
                .andExpect(jsonPath("$.type").value(creationDto.getType().name()))
                .andExpect(jsonPath("$.number").value(creationDto.getNumber()))
                .andExpect(jsonPath("$.description").value(creationDto.getDescription()))
                .andExpect(jsonPath("$.balance").value(creationDto.getBalance()))
                ;
    }

}