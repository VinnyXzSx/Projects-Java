package Construtores.emprestimoslivros;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BibliotecaSistema sistema = new BibliotecaSistema();
        while (true){
            System.out.println("==========================");
            System.out.println("[1] - Cadastrar usuário");
            System.out.println("[2] - Cadastrar Livro ");
            System.out.println("[3] - Realizar Empréstimo");
            System.out.println("[4] - Encerrar Programa");
            System.out.println("==========================");
            System.out.print("Opção: "); char op = sc.nextLine().charAt(0);
            switch (op){
                case '1' -> sistema.cadastrarUsuário();
                case '2' -> sistema.cadastrarLivros();
                case '3' -> sistema.realizarEmprestimo();
                case '4' -> {
                    System.out.println("Encerrando Programa... ");
                    return;
                }
                default ->  System.out.println("Opção inválida");
            }
        }
    }
}
