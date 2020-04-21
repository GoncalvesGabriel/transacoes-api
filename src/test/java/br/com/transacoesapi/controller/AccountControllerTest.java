package br.com.transacoesapi.controller;

import static br.com.transacoesapi.helper.JsonTestHelper.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.transacoesapi.dto.account.AccountDTO;
import br.com.transacoesapi.dto.account.CreateAccountDTO;
import br.com.transacoesapi.service.AccountService;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AccountService service;

  @InjectMocks
  private AccountController controller;

  @Before
  public void setup(){
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void createSuccess() throws Exception {

    String documentNumber = "12345678900";
    CreateAccountDTO createDTO = new CreateAccountDTO(documentNumber);
    AccountDTO accountDTO = new AccountDTO(1L, documentNumber);
    Mockito.when(service.create(createDTO)).thenReturn(accountDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/accounts")
        .content(asJsonString(createDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.account_id").value(accountDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.document_number").value(accountDTO.getDocumentNumber()));
  }

  @Test
  public void getByIdSuccess() throws Exception {
    Long accountId = 1L;
    String documentNumber = "12345678900";
    AccountDTO accountDTO = new AccountDTO(1L, documentNumber);
    Mockito.when(service.findDTOById(1L)).thenReturn(Optional.of(accountDTO));
    mockMvc.perform(MockMvcRequestBuilders
        .get("/accounts/{0}", accountId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.account_id").value(accountDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.document_number").value(accountDTO.getDocumentNumber()));
  }

  @Test
  public void getByIdNotFound() throws Exception {
    Long transactionId = 1L;
    Mockito.when(service.findDTOById(1L)).thenReturn(Optional.empty());
    mockMvc.perform(MockMvcRequestBuilders
        .get("/accounts/{0}", transactionId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}