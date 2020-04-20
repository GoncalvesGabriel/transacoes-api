package br.com.transacoesapi.controller;

import br.com.transacoesapi.entity.dto.account.AccountDTO;
import br.com.transacoesapi.entity.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.entity.dto.transaction.TransactionDTO;
import br.com.transacoesapi.service.TransactionService;
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

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private TransactionService service;

  @PostMapping
  public ResponseEntity<TransactionDTO> create(@Valid @RequestBody CreateTransactionDTO createTransactionDTO) {
    TransactionDTO transactionDTO = service.create(createTransactionDTO);
    URI uri = URI.create(String.format("/transaction/%s", transactionDTO.getId()));
    return ResponseEntity.created(uri).body(transactionDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransactionDTO> getById(@PathVariable Long id) {
    Optional<TransactionDTO> transaction = service.findBy(id);
    return ResponseEntity.of(transaction);
  }

}
