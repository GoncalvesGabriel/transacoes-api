package br.com.transacoesapi.controller;

import br.com.transacoesapi.dto.account.AccountDTO;
import br.com.transacoesapi.dto.account.CreateAccountDTO;
import br.com.transacoesapi.service.AccountService;
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
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService service;

  @PostMapping
  @ApiOperation(value = "Create new account", consumes = "application/json", httpMethod = "POST")
  @ApiResponse(code = 201, message = "Account successfully created")
  public ResponseEntity<AccountDTO> create(@Valid @RequestBody CreateAccountDTO createAccountDTO) {
    AccountDTO accountDTO = service.create(createAccountDTO);
    URI uri = URI.create(String.format("/accounts/%s", accountDTO.getId()));
    return ResponseEntity.created(uri).body(accountDTO);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Get an account", consumes = "application/json", httpMethod = "GET")
  @ApiResponse(code = 200, message = "Return a account with id")
  public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
    Optional<AccountDTO> account = service.findDTOById(id);
    return ResponseEntity.of(account);
  }
}
