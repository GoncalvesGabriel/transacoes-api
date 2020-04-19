package br.com.transacoesapi.entity.enux;

import lombok.Getter;

public @Getter
enum OperationType {

  COMPRA_A_VISTA(1, "COMPRA A VISTA"),

  COMPRA_PARCELADA(2, "COMPRA PARCELADA"),

  SAQUE(3, "SAQUE"),

  PAGAMENTO(4, "PAGAMENTO");

  private Integer id;

  private String description;

  OperationType(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public static OperationType getEnum(Integer id) {
    for (OperationType value : OperationType.values()) {
      if (value.getId().equals(id)) {
        return value;
      }
    }
    throw new RuntimeException(String.format("Tipo de operação (%s) não suportado", id));
  }
}
