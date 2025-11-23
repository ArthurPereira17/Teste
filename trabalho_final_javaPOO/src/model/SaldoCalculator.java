package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaldoCalculator {

    public static class ResultadoSaldo {
        private double valorTotal;
        private Map<Produto, Integer> quantidadesPorProduto;

        public ResultadoSaldo(double valorTotal, Map<Produto, Integer> quantidadesPorProduto) {
            this.valorTotal = valorTotal;
            this.quantidadesPorProduto = quantidadesPorProduto;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public Map<Produto, Integer> getQuantidadesPorProduto() {
            return quantidadesPorProduto;
        }
    }

    public ResultadoSaldo calcularSaldo(List<MovimentoEstoque> movimentos) {


        ControleEstoque controle = new ControleEstoque();
        List<Produto> produtos = controle.getTodosProdutos();

        Map<Produto, Integer> quantidades = new HashMap<>();
        double total = 0.0;

        for (Produto p : produtos) {
            quantidades.put(p, p.getQuantidade());
            total += p.getQuantidade() * p.getPrecoUnitario();
        }

        return new ResultadoSaldo(total, quantidades);
    }
}