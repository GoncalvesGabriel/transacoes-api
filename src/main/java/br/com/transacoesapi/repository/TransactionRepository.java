package br.com.transacoesapi.repository;

import br.com.transacoesapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
