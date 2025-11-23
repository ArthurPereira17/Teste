@startuml
!theme plain

package model {
  abstract class MovimentoEstoque {
    - data: LocalDate
    - produto: Produto
    - quantidade: int
    + MovimentoEstoque(data: LocalDate, produto: Produto, quantidade: int)
    + getData(): LocalDate
    + setData(data: LocalDate): void
    + getProduto(): Produto
    + setProduto(produto: Produto): void
    + getQuantidade(): int
    + setQuantidade(quantidade: int): void
    + compareTo(outro: MovimentoEstoque): int
  }

  class EntradaEstoque {
    - valorUnitario: double
    + EntradaEstoque(data: LocalDate, produto: Produto, quantidade: int, valorUnitario: double)
    + getValorUnitario(): double
    + setValorUnitario(valorUnitario: double): void
  }

  class SaidaEstoque {
    + SaidaEstoque(data: LocalDate, produto: Produto, quantidade: int)
  }

  class Produto {
    - proximoCodigo: int {static}
    - codigo: int
    - nome: String
    - precoUnitario: double
    - quantidade: int
    - categoria: Categoria
    + Produto(nome: String, precoUnitario: double, quantidade: int, categoria: Categoria)
    + Produto(codigo: int, nome: String, precoUnitario: double, quantidade: int, categoria: Categoria)
    + getCodigo(): int
    + setCodigo(codigo: int): void
    + getNome(): String
    + setNome(nome: String): void
    + getPrecoUnitario(): double
    + setPrecoUnitario(precoUnitario: double): void
    + getQuantidade(): int
    + setQuantidade(quantidade: int): void
    + getCategoria(): Categoria
    + setCategoria(categoria: Categoria): void
    + calcularValorTotal(): double
    + getNomeCategoria(): String
    + adicionarQuantidade(quantidade: int): void
    + removerQuantidade(quantidade: int): void
    + getQuantidadeEstoque(): int
  }

  class ControleEstoque {
    - produtos: ArrayList<Produto>
    - movimentos: ArrayList<MovimentoEstoque>
    - PRODUTOS_CSV: String {static}
    - MOVIMENTOS_CSV: String {static}
    - CSV_SEPARATOR: String {static}
    + ControleEstoque()
    + adicionarProduto(produto: Produto): void
    + registrarEntrada(entrada: EntradaEstoque): void
    + registrarSaida(saida: SaidaEstoque): void
    + listarMovimentos(): void
    + consultarSaldoAtual(): void
    + gravarDados(): void
    - gravarProdutos(): void
    - gravarMovimentos(): void
    - recuperarProdutos(): void
    - recuperarMovimentos(): void
    + getCategoriaPorNome(nome: String): Categoria
    + findProdutoPorCodigo(codigo: int): Produto
    + excluirProduto(indice: int): void
    + getTodosProdutos(): List<Produto>
    + calcularSaldoAtual(): SaldoCalculator.ResultadoSaldo
    + getMovimentoEstoques(): List<MovimentoEstoque>
  }

  class SaldoCalculator {
    + calcularSaldo(movimentos: List<MovimentoEstoque>): ResultadoSaldo
    
    class ResultadoSaldo {
      - valorTotal: double
      - quantidadesPorProduto: Map<Produto, Integer>
      + ResultadoSaldo(valorTotal: double, quantidadesPorProduto: Map<Produto, Integer>)
      + getValorTotal(): double
      + getQuantidadesPorProduto(): Map<Produto, Integer>
    }
  }

  class Main {
    + main(args: String[]): void
  }
}

package "model.categorias" {
  abstract class Categoria {
    - nome: String
    + Categoria(nome: String)
    + getNome(): String
    + setNome(nome: String): void
  }

  class ComponenteHardware {
    + ComponenteHardware()
  }

  class Periferico {
    + Periferico()
  }

  class Acessorio {
    + Acessorio()
  }

  class OutroProduto {
    + OutroProduto()
  }
}

package view {
  class TelaPrincipal {
    - cardLayout: CardLayout
    - painelConteudo: JPanel
    - controleEstoque: ControleEstoque
    - telaListarMovimentos: TelaListarMovimentos
    - telaConsultarSaldo: TelaConsultarSaldo
    - telaProdutos: TelaCadastroProdutos
    + TelaPrincipal()
    - mostrarTela(nome: String): void
  }

  class AppLojaInformatica {
    + main(args: String[]): void
  }

  class PainelLateral {
    - btnProdutos: JButton
    - btnEntrada: JButton
    - btnSaida: JButton
    - btnSaldo: JButton
    - btnMovimentos: JButton
    + PainelLateral()
    - criarBotao(texto: String): JButton
    + onProdutos(r: Runnable): void
    + onEntrada(r: Runnable): void
    + onSaida(r: Runnable): void
    + onSaldo(r: Runnable): void
    + onMovimentos(r: Runnable): void
  }
}

package "view.CadastroProduto" {
  class TelaCadastroProdutos {
    - controle: ControleEstoque
    - painelFormulario: PainelFormularioProduto
    - painelTabela: PainelTabelaProdutos
    + TelaCadastroProdutos()
    + getPainelTabelaProdutos(): PainelTabelaProdutos
  }

  class PainelFormularioProduto {
    - campoNome: JTextField
    - campoPrecoUni: JTextField
    - campoQuantidade: JTextField
    - comboCategoria: JComboBox<String>
    - botaoCadastrar: JButton
    + PainelFormularioProduto()
    + definirAcaoBotaoCadastrar(acao: ActionListener): void
    + obterProdutoDoForulario(controle: ControleEstoque): Produto
    + limparFormulario(): void
  }

  class PainelTabelaProdutos {
    - tabela: JTable
    - modeloTabela: DefaultTableModel
    - botaoRemover: JButton
    + PainelTabelaProdutos()
    + atualizarTabela(produtos: List<Produto>): void
    + obterLinhaSelecionada(): int
    + definirAcaoBotaoRemover(acao: ActionListener): void
  }
}

package "view.ConsularSaldo" {
  class TelaConsultarSaldo {
    - controle: ControleEstoque
    - lblQuantidade: JLabel
    - lblValorTotal: JLabel
    - lblCustoMedio: JLabel
    + TelaConsultarSaldo(controle: ControleEstoque)
    + atualizarSaldo(): void
  }
}

package "view.ListarMovimentos" {
  class TelaListarMovimentos {
    - tabela: JTable
    - modeloTabela: DefaultTableModel
    - controle: ControleEstoque
    + TelaListarMovimentos(controle: ControleEstoque)
    + carregarMovimentosDoCSV(): void
  }
}

package "view.RegistrarEntrada" {
  class TelaRegistrarEntrada {
    - controle: ControleEstoque
    - telaListarMovimentos: TelaListarMovimentos
    + TelaRegistrarEntrada(controle: ControleEstoque, telaListarMovimentos: TelaListarMovimentos)
  }

  class PainelTabelaEntrada {
    - tabela: JTable
    - modelo: DefaultTableModel
    + PainelTabelaEntrada()
    + carregarEntradas(movimentos: List<MovimentoEstoque>): void
  }
}

package "view.RegistrarSaida" {
  class TelaRegistrarSaida {
    - controle: ControleEstoque
    - telaListarMovimentos: TelaListarMovimentos
    + TelaRegistrarSaida(controle: ControleEstoque, telaListarMovimentos: TelaListarMovimentos)
    + parseDataDoCampo(txtData: JTextField): LocalDate
  }

  class PainelTabelaSaida {
    - tabela: JTable
    - modelo: DefaultTableModel
    + PainelTabelaSaida()
    + carregarSaidas(movimentos: List<MovimentoEstoque>): void
  }
}

' Relações de Herança
MovimentoEstoque <|-- EntradaEstoque
MovimentoEstoque <|-- SaidaEstoque
Categoria <|-- ComponenteHardware
Categoria <|-- Periferico
Categoria <|-- Acessorio
Categoria <|-- OutroProduto

' Relações de Agregação/Composição
MovimentoEstoque --> Produto
EntradaEstoque --> Produto
SaidaEstoque --> Produto
ControleEstoque *--> Produto
ControleEstoque *--> MovimentoEstoque
Produto --> Categoria
SaldoCalculator --> Produto
TelaPrincipal *--> ControleEstoque
TelaPrincipal *--> TelaListarMovimentos
TelaPrincipal *--> TelaConsultarSaldo
TelaPrincipal *--> TelaCadastroProdutos
TelaPrincipal *--> PainelLateral
TelaRegistrarSaida *--> PainelTabelaSaida
TelaRegistrarEntrada *--> PainelTabelaEntrada
TelaCadastroProdutos *--> PainelFormularioProduto
TelaCadastroProdutos *--> PainelTabelaProdutos

' Relações de Associação
TelaListarMovimentos --> ControleEstoque
TelaConsultarSaldo --> ControleEstoque
TelaRegistrarEntrada --> ControleEstoque
TelaRegistrarEntrada --> TelaListarMovimentos
TelaRegistrarSaida --> ControleEstoque
TelaRegistrarSaida --> TelaListarMovimentos
TelaCadastroProdutos --> ControleEstoque
PainelTabelaSaida --> SaidaEstoque
PainelTabelaSaida --> MovimentoEstoque
PainelTabelaEntrada --> EntradaEstoque
PainelTabelaEntrada --> MovimentoEstoque
PainelTabelaProdutos --> Produto
PainelFormularioProduto --> Produto

' Relações de Dependência
ControleEstoque ..> Categoria
ControleEstoque ..> SaldoCalculator
Main ..> ControleEstoque
Main ..> Produto
Main ..> EntradaEstoque
Main ..> SaidaEstoque
Main ..> Categoria
AppLojaInformatica ..> TelaPrincipal
TelaPrincipal ..> TelaRegistrarEntrada
TelaPrincipal ..> TelaRegistrarSaida
TelaRegistrarSaida ..> Produto
TelaRegistrarSaida ..> SaidaEstoque
TelaRegistrarEntrada ..> Produto
TelaRegistrarEntrada ..> EntradaEstoque
TelaListarMovimentos ..> BufferedReader
TelaListarMovimentos ..> FileReader
TelaConsultarSaldo ..> SaldoCalculator
TelaConsultarSaldo ..> Produto
PainelFormularioProduto ..> ControleEstoque
PainelFormularioProduto ..> Categoria

' Classe interna
SaldoCalculator *-- ResultadoSaldo

@enduml