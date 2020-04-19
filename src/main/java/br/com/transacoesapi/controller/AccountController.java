package br.com.transacoesapi.controller;

import br.com.transacoesapi.entity.dto.account.AccountDTO;
import br.com.transacoesapi.entity.dto.account.CreateAccountDTO;
import br.com.transacoesapi.service.AccountService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService service;

  @PostMapping
  public ResponseEntity<AccountDTO> create(@RequestBody CreateAccountDTO createAccountDTO) {
    AccountDTO accountDTO = service.create(createAccountDTO);
    URI uri = URI.create(String.format("/accounts/%s", accountDTO.getId()));
    return ResponseEntity.created(uri).body(accountDTO);
  }
}
