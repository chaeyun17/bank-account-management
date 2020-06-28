package org.yunfactory.bank_account_management.bank_account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.yunfactory.bank_account_management.bank_account.dto.AccountCreationDto;
import org.yunfactory.bank_account_management.bank_account.service.AccountService;


@ExtendWith(value = {SpringExtension.class, MockitoExtension.class})
public class AccountControllerTest {

    private MockMvc mvc;

    @Mock
    private AccountService accountService;

    private AccountCreationDto accountCreationDto;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        this.mvc = MockMvcBuilders.standaloneSetup(new AccountController(accountService)).build();
        accountCreationDto = new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "112", "description", 10);
        mapper = new ObjectMapper();
    }
    
    @Test
    @DisplayName("Account 정보로, 저장할 시, CREATED 반환")
    public void Given_account_When_Save_Tuen_Returned_OK() throws Exception{
        // given
        String content = mapper.writeValueAsString(accountCreationDto);
        
        // when then
        mvc.perform(post("/api/accounts").header("content-type", "application/json").content(content))
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("잔고가 음수로, 저장할 시, BadRequest 반환")
    public void Given_minus_balance_When_Save_Then_Returned_BadRequest() throws Exception{
        // given
        accountCreationDto.setBalance(-99);
        String content = mapper.writeValueAsString(accountCreationDto);

        // when then
        mvc.perform(post("/api/accounts").header("content-type", "application/json").content(content))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Null 값으로, 저장할 시, BadRequest 반환")
    public void Given_null_when_Save_Then_Returned_BadRquest() throws Exception{
        // given
        accountCreationDto.setBankName(null);
        String content = mapper.writeValueAsString(accountCreationDto);

        // when then
        mvc.perform(post("/api/accounts").header("content-type", "application/json").content(content))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Update Validation 테스트")
    public void Update_Validate_Test() throws Exception{
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", -999L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, "number", null, 0L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto("purpose", "bankName", ACCOUNT_TYPE.CHECKING, null, "description", 0L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto("purpose", "bankName", null, "number", "description", 0L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto(null, "bankName", ACCOUNT_TYPE.CHECKING, "number", "description", 0L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto("purpose", null, ACCOUNT_TYPE.CHECKING, "number", "description", 0L));
        Given_Invalid_Values_When_Update_Then_Throws_BadRequest(new AccountCreationDto());
    }

    private void Given_Invalid_Values_When_Update_Then_Throws_BadRequest(AccountCreationDto creationDto) throws Exception{
        // given
        String requestBody = mapper.writeValueAsString(creationDto);

        // when then
        ResultActions result = mvc.perform(put("/api/accounts/{id}", 1L)
                                            .header("content-type", "application/json; charset=utf-8")
                                            .content(requestBody));
        
        // then
        result.andDo(print())
                .andExpect(status().isBadRequest());


    }


}