package Construtores.emprestimoslivros;


import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaSistema {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();


    //CADASTRAR: LIVROS, USUÁIO, EMPÉSTIMO

    // ========== USERS ============
    public void cadastrarUsuário() {
        while (true) {
            int idUser = usuarios.size() + 1;
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("Senha: "); String senha = sc.nextLine();
            usuarios.add(new Usuario(idUser, nome, senha));
            System.out.println("Usuário Cadastrado com ID: " + idUser + "! ");
            System.out.println("-------------------");
            System.out.print("Continuar? ");
            if (sc.nextLine().toLowerCase().charAt(0) != 's') break;
        }
    }

    public void cadastrarLivros() {
        while (true) {
            int idLivro = livros.size() + 1;
            System.out.print("Título: "); String titulo = sc.nextLine();
            System.out.print("Autor: ");String autor = sc.nextLine();
            System.out.print("Estoque: ");int estoque = sc.nextInt(); sc.nextLine();
            livros.add(new Livro(idLivro, titulo, autor, estoque));
            System.out.println("Livro Cadastrado!");
            System.out.println("-------------------");
            System.out.print("Continuar? ");
            if (sc.nextLine().toLowerCase().charAt(0) != 's') break;
        }
    }

    public void realizarEmprestimo() {
        while (true) {
            System.out.print("ID do Usuário: "); int idUser = sc.nextInt();sc.nextLine();
            System.out.print("Senha: "); String senha = sc.nextLine();
            Usuario usuario = autenticar(idUser,senha);
            if (usuario == null) {
                System.out.println("Acesso negado! Digite novamente");
                continue;
            }
            System.out.println("Acesso sob permissão de " + usuario.getNome() + ". ");
            while (true){
                listarLivros();
                buscarLivro();
                System.out.println("-------------------");
                System.out.print("Continuar? ");
                if (sc.nextLine().toLowerCase().charAt(0) != 's') return;
            }

        }
    }

    private void listarLivros() {

        System.out.println("--------------------------------------------------------------");
        System.out.printf("| %-3s | %-25s | %-18s | %-7s |\n",
                "ID", "TÍTULO", "AUTOR", "ESTOQUE");
        System.out.println("--------------------------------------------------------------");

        for (Livro l : livros) {
            System.out.printf("| %-3d | %-25s | %-18s | %-7d |\n",
                    l.getIdLivro(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getEstoque());
        }

        System.out.println("--------------------------------------------------------------");
    }
    private Usuario autenticar(int id, String senha) {
        for (Usuario u : usuarios) {
            if (u.getId() == id && u.getSenha().equals(senha)) {
                return u;
            }
        }

        return null;
    }

    private void buscarLivro(){
        while (true){
            System.out.print("ID do Livro: "); int id = sc.nextInt();
            System.out.print("Quantidade: "); int qntd = sc.nextInt(); sc.nextLine();
            boolean encontrado = false;

            for (Livro l : livros){
                if (id == l.getIdLivro()){
                    encontrado = true;
                    if (qntd >= 0 && qntd <= l.getEstoque()){
                        l.setEstoque(qntd);
                        System.out.println("Empréstimo realizado!");
                        return;
                    } else if(qntd > l.getEstoque()){
                        System.out.println("Estoque insulficiente! ");
                    } else {
                        System.out.println("Erro... Digite uma quantidade válida! ");
                    }
                }
            }
            if (!encontrado) {
                System.out.println("Livro não econtrado. Digite novamente!");
            }
        }
    }


}



