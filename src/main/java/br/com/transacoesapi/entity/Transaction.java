package br.com.transacoesapi.entity;


import br.com.transacoesapi.entity.converter.OperationTypeConverter;
import br.com.transacoesapi.entity.enux.OperationType;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name =  "Transaction")
public @Data
class Transaction {

  @Id
  @Column(name = "Transaction_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "Account_ID")
  private Account account;


  @Column(name = "OperationType_ID")
  @Convert(converter = OperationTypeConverter.class)
  private OperationType operationType;

  @Column(name = "Amount")
  private double amount;


  private LocalDateTime eventDate;

}
