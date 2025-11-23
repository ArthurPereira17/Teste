package model;
import java.time.LocalDate;

import model.categorias.*; 

public class Main {
    public static void main(String[] args) {

        ControleEstoque controle = new ControleEstoque();

        Categoria hardware = new ComponenteHardware();
        Categoria periferico = new Periferico();
        //add 10 ssds de inicio ao cadastrar
        Produto p1 = new Produto("SSD Kingston 480GB", 250.00, 10, hardware);
        Produto p2 = new Produto("Mouse Logitech", 70.00, 5, periferico);
        Produto p3 = new Produto("Mouse Logitech", 70.00, 5, periferico);


        controle.adicionarProduto(p1);
        controle.adicionarProduto(p2);
        //add mais 5 ssds, totalizando 15
        EntradaEstoque entrada1 = new EntradaEstoque(LocalDate.of(2025, 11, 10), p1, 5, 230.00);
        EntradaEstoque entrada2 = new EntradaEstoque(LocalDate.of(2025, 11, 11), p2, 10, 60.00);

        controle.registrarEntrada(entrada1);
        controle.registrarEntrada(entrada2);
        //remove 3 ssds, ficando em 12
        SaidaEstoque saida1 = new SaidaEstoque(LocalDate.of(2025, 11, 12), p1, 3);
        SaidaEstoque saida2 = new SaidaEstoque(LocalDate.of(2025, 11, 13), p2, 4);

        controle.registrarSaida(saida1);
        controle.registrarSaida(saida2);
        //printa 12 ssds
        System.out.println("\n SALDO ATUAL DO ESTOQUE:");
        controle.consultarSaldoAtual();
        
        //lista as movimentacoes
        System.out.println("\n MOVIMENTOS ORDENADOS POR DATA:");
        controle.listarMovimentos();
    }
}
