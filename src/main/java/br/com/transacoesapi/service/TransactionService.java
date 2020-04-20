package br.com.transacoesapi.service;

import br.com.transacoesapi.entity.Transaction;
import br.com.transacoesapi.entity.dto.transaction.CreateTransactionDTO;
import br.com.transacoesapi.entity.dto.transaction.TransactionDTO;
import br.com.transacoesapi.repository.TransactionRepository;
import br.com.transacoesapi.service.builder.EntityBuilder;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TransactionService {

  private TransactionRepository repository;

  private EntityBuilder<Transaction, CreateTransactionDTO> createBuilder;

  @Autowired
  public TransactionService(TransactionRepository repository, EntityBuilder<Transaction, CreateTransactionDTO> createBuilder) {
    this.repository = repository;
    this.createBuilder = createBuilder;
  }

  public TransactionDTO create(CreateTransactionDTO createTransactionDTO) {
    Transaction transaction = createBuilder.builder(createTransactionDTO);
    repository.save(transaction);
    return new TransactionDTO(transaction);
  }

  public Optional<TransactionDTO> findBy(Long id) {
    Optional<Transaction> transaction = repository.findById(id);
    if (transaction.isPresent()) {
      return Optional.of(new TransactionDTO(transaction.get()));
    }
    return Optional.empty();
  }
}
