package br.com.transacoesapi.controller;


import static br.com.transacoesapi.helper.JsonTestHelper.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.transacoesapi.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.dto.transaction.TransactionDTO;
import br.com.transacoesapi.entity.enux.OperationType;
import br.com.transacoesapi.service.TransactionService;
import java.time.LocalDateTime;
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
public class TransactionControllerTest {

  private MockMvc mockMvc;

  @Mock
  private TransactionService service;

  @InjectMocks
  private TransactionController controller;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void create() throws Exception {
    CreateTransactionDTO createDTO = CreateTransactionDTO.builder().accountId(1L).amount(100).operationType(OperationType.COMPRA_A_VISTA.getId()).build();
    TransactionDTO transactionDTO = TransactionDTO.builder().id(1L).accountId(1L).amount(100).eventDate(LocalDateTime.now()).operationType(OperationType.COMPRA_A_VISTA).build();
    Mockito.when(service.create(createDTO)).thenReturn(transactionDTO);

    mockMvc.perform(MockMvcRequestBuilders
        .post("/transactions")
        .content(asJsonString(createDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.transaction_id").value(transactionDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.account").value(transactionDTO.getId().intValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operation_type").value(is(transactionDTO.getOperationType().getDescription())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(is(transactionDTO.getAmount())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.event_date").exists());
  }

  @Test
  public void getByIdSuccess() throws Exception {
    Long transactionId = 1L;
    TransactionDTO transactionDTO = TransactionDTO.builder().id(transactionId).accountId(1L).amount(100).eventDate(LocalDateTime.now()).operationType(OperationType.COMPRA_A_VISTA).build();
    Mockito.when(service.findDTOById(1L)).thenReturn(Optional.of(transactionDTO));
    mockMvc.perform(MockMvcRequestBuilders
        .get("/transactions/{0}", transactionId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.transaction_id").value(transactionDTO.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.account").value(transactionDTO.getId().intValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.operation_type").value(is(transactionDTO.getOperationType().getDescription())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(is(transactionDTO.getAmount())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.event_date").exists());
  }

  @Test
  public void getByIdNotFound() throws Exception {
    Long transactionId = 1L;
    Mockito.when(service.findDTOById(1L)).thenReturn(Optional.empty());
    mockMvc.perform(MockMvcRequestBuilders
        .get("/transactions/{0}", transactionId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}