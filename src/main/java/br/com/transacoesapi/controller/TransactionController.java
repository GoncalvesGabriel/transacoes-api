package br.com.transacoesapi.controller;

import br.com.transacoesapi.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.dto.transaction.TransactionDTO;
import br.com.transacoesapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.net.URI;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private TransactionService service;

  @PostMapping
  @ApiOperation(value = "Create new transaction", consumes = "application/json", httpMethod = "POST")
  @ApiResponse(code = 201, message = "Transaction successfully created")
  public ResponseEntity<TransactionDTO> create(@Valid @RequestBody CreateTransactionDTO createTransactionDTO) {
    TransactionDTO transactionDTO = service.create(createTransactionDTO);
    URI uri = URI.create(String.format("/transaction/%s", transactionDTO.getId()));
    return ResponseEntity.created(uri).body(transactionDTO);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get a transaction", consumes = "application/json", httpMethod = "GET")
  @ApiResponse(code = 200, message = "Return a transaction with id")
  public ResponseEntity<TransactionDTO> getById(@PathVariable Long id) {
    Optional<TransactionDTO> transaction = service.findDTOById(id);
    return ResponseEntity.of(transaction);
  }

}
