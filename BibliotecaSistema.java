package emprestimoslivros;


import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaSistema {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Registro> registros = new ArrayList<>();
    private int idAdmin = 132545;
    private String senhaAdmin= "B1scoit0";
    //MENU's
    public void menuPrincipal(){
        int op;
        while (true){
            System.out.println("=======================");
            System.out.println("[1] - Login. ");
            System.out.println("[2] - Cadastrar Usuário.");
            System.out.println("[3] - Encerrar Programa.");
            while (true){
                try{
                    System.out.print("Opção: "); op = Integer.parseInt(sc.nextLine()); break;
                }catch (NumberFormatException e){
                    System.out.println("Erro, Digite uma Opção Válida");
                }
            }

            switch (op){
                case 1 -> login();
                case 2 -> cadastrarUsuario();
                case 3 -> System.exit(0);
            }
        }


    }


    public void menuAdmin(){
        int op;
        while (true){
            System.out.println("==========================");
            System.out.println("[1] - Cadastrar Livro");
            System.out.println("[2] - Listar Livros ");
            System.out.println("[3] - Listar Usuários ");
            System.out.println("[4] - Registro de Empréstimo");
            System.out.println("[5] - Mudar Senha ou ID (ADMIN)");
            System.out.println("[6] - Mudar Senha do (USUÁRIO)");
            System.out.println("[7] - Sair ");
            System.out.println("==========================");
            while (true){
                try{
                    System.out.print("Opção: "); op = Integer.parseInt(sc.nextLine()); break;
                }catch (NumberFormatException e){
                    System.out.println("Erro, Digite uma opção válida...");
                }
            }
            switch (op){
                case 1 -> cadastrarLivros();
                case 2 -> listarLivros();
                case 3 -> listarUsuarios();
                case 4 -> registrodeEmprestimos();
                case 5 -> mudarSenhaAdim();
                case 6 -> mudarSenhaUsuario();
                case 7 -> {
                    System.out.println("Saindo... ");
                    return;
                }
                default ->  System.out.println("Opção inválida");
            }
        }
    }

    //LOGIN:

    public void login(){
        int id;
        String senha;

        while (true){
            try{
                System.out.print("ID: ");
                id = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID inválido!");
                continue;
            }

            System.out.print("Senha: ");
            senha = sc.nextLine();

            // ADMIN
            if (id == idAdmin && senha.equals(senhaAdmin)){
                System.out.println("Bem-vindo, Admin!");
                menuAdmin();
                return;
            }

            // USUÁRIO
            Usuario usuario = autenticar(id, senha);
            if (usuario != null){
                System.out.println("Bem-vindo, " + usuario.getNome());
                menuUsuario();
                return;
            }

            System.out.println("ID ou senha incorretos.");
        }
    }



    //ALTERAR SENHA / ID:

    public void mudarSenhaUsuario(){
        while (true){
            int id;
            try{
                System.out.print("ID do Usuário: ");
                id = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("ID inválido.");
                continue;
            }

            for (Usuario u : usuarios){
                if (u.getId() == id){
                    System.out.print("Nova senha: ");
                    String nova = sc.nextLine();
                    System.out.print("Confirmar senha: ");
                    String confirmar = sc.nextLine();

                    if (nova.equals(confirmar)){
                        u.setSenha(nova);
                        System.out.println("Senha alterada com sucesso!");
                        return;
                    } else {
                        System.out.println("As senhas não coincidem.");
                        return;
                    }
                }
            }

            System.out.println("Usuário não encontrado.");
            return;
        }
    }

    public void mudarSenhaAdim(){
        int op;
        System.out.println("[1] - mudar ID ");
        System.out.println("[2] - mudar Senha");
        while (true){
            try{
                System.out.print("Opção: "); op = Integer.parseInt(sc.nextLine()); break;
            }catch (NumberFormatException e){
                System.out.println("Erro, Digite uma opção válida...");
            }
        }
        if (op == 1){
            int newIdAdmin;
            while (true){
                while (true){
                    try{
                        System.out.print("Digite o novo ID: "); newIdAdmin = Integer.parseInt(sc.nextLine()); break;
                    }catch (NumberFormatException e){
                        System.out.println("Erro, ID inválido...");
                    }
                }
                if (newIdAdmin != idAdmin){
                    idAdmin = newIdAdmin;
                    System.out.println("ID alterado com sucesso!");
                    return;
                } else {
                    System.out.println("Já está utilizando esse ID :)");
                }
            }

        }

        while (true){
            String newSenha; String confirmar;
            System.out.print("Digite a nova senha: "); newSenha = sc.nextLine();
            System.out.print("Digite novamente para confirmar: "); confirmar = sc.nextLine();
            if (newSenha.equals(confirmar)){
                senhaAdmin = newSenha;
                System.out.println("Senha alterada com sucesso! ");
                return;
            }
        }

    }


    public void menuUsuario(){
        int op;
        while (true){
            System.out.println("==========================");
            System.out.println("[1] - Realizar Empréstimo");
            System.out.println("[2] - Listar Livros ");
            System.out.println("[3] - Sair");
            System.out.println("==========================");
            while (true){
                try{
                    System.out.print("Opção: "); op = Integer.parseInt(sc.nextLine()); break;
                }catch (NumberFormatException e){
                    System.out.println("Erro, Digite uma opção válida...");
                }
            }
            switch (op){
                case 1 -> realizarEmprestimo();
                case 2 -> listarLivros();
                case 3 -> {
                    System.out.println("Saindo... ");
                    return;
                }
                default ->  System.out.println("Opção inválida");
            }
        }

    }








    //CADASTRAR: LIVROS, USUÁIO, EMPÉSTIMO

    // ========== USERS ============
    public void cadastrarUsuario() {
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
    //===========LIVROS==============
    public void cadastrarLivros() {
        while (true) {
            int estoque;
            int idLivro = livros.size() + 1;
            System.out.print("Título: "); String titulo = sc.nextLine();
            System.out.print("Autor: ");String autor = sc.nextLine();
            while(true){
                try{
                    System.out.print("Estoque: "); estoque = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Erro, Digite um valor válido...");
                }
            }
            livros.add(new Livro(idLivro, titulo, autor, estoque));
            System.out.println("Livro Cadastrado!");
            System.out.println("-------------------");
            System.out.print("Continuar? ");
            if (sc.nextLine().toLowerCase().charAt(0) != 's') break;
        }
    }





    // LISTAGENS
    //==========LIVROS============
    private void listarLivros() {
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("| %-3s | %-25s | %-18s | %-7s |\n", "ID", "TÍTULO", "AUTOR", "ESTOQUE");
        System.out.println("-------------------------------------------------------------------");

        for (Livro l : livros) {
            System.out.printf("| %-3d | %-25s | %-18s | %-7d |\n",
                    l.getIdLivro(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getEstoque());
        }

        System.out.println("--------------------------------------------------------------");
    }
    //US
    public void listarUsuarios(){
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-3s | %-20s | \n",
                "ID", "USUÁRIO");
        System.out.println("----------------------------------------------------------");
        for (Usuario u : usuarios){
            System.out.printf("| %-3d | %-20s |\n",
                    u.getId(),
                    u.getNome());
        }
    }

    public void registrodeEmprestimos(){
        if (registros.isEmpty()){
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        System.out.println("---------------------------------------------------------------------");
        System.out.printf("| %-15s | %-20s | %-5s | %-10s |\n",
                "USUÁRIO", "LIVRO", "QTD", "DATA");
        System.out.println("---------------------------------------------------------------------");

        for (Registro r : registros){
            System.out.printf("| %-15s | %-20s | %-5d | %-10s |\n",
                    r.getUsuario().getNome(),
                    r.getLivro().getTitulo(),
                    r.getQuantidade(),
                    r.getData());
        }

        System.out.println("---------------------------------------------------------------------");
    }



    //AUTENTICAÇÃO DE USUÁRIO

    private Usuario autenticar(int id, String senha) {
        for (Usuario u : usuarios) {
            if (u.getId() == id && u.getSenha().equals(senha)) {
                return u;
            }
        }

        return null;
    }






    public void realizarEmprestimo() {

        int idUser;
        while (true) {
            try {
                System.out.print("ID do Usuário: ");
                idUser = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro, digite um ID válido...");
            }
        }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = autenticar(idUser, senha);
        if (usuario == null) {
            System.out.println("Acesso negado!");
            return;
        }

        System.out.println("Acesso autorizado para " + usuario.getNome());

        while (true) {
            listarLivros();


            int idLivro = buscarLivro();

            if (idLivro == -1) {
                System.out.println("Empréstimo cancelado.");
                return;
            }

            Livro livroEmprestado = null;
            for (Livro l : livros) {
                if (l.getIdLivro() == idLivro) {
                    livroEmprestado = l;
                    break;
                }
            }

            // Registrar o empréstimo
            registros.add(new Registro(usuario, livroEmprestado, 1));

            System.out.println("Empréstimo registrado com sucesso!");

            System.out.print("Deseja emprestar outro livro? ");
            if (sc.nextLine().toLowerCase().charAt(0) != 's') {
                return;
            }
        }
    }


    public int buscarLivro() {

        int idLivro;
        int quantidade;

        while (true) {

            // Ler ID do livro
            try {
                System.out.print("ID do Livro (0 para cancelar): ");
                idLivro = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID inválido.");
                continue;
            }

            // Cancelamento
            if (idLivro == 0) {
                return -1;
            }

            // Procurar livro
            Livro livroEncontrado = null;
            for (Livro l : livros) {
                if (l.getIdLivro() == idLivro) {
                    livroEncontrado = l;
                    break;
                }
            }

            if (livroEncontrado == null) {
                System.out.println("Livro não encontrado.");
                continue;
            }

            // Ler quantidade
            try {
                System.out.print("Quantidade: ");
                quantidade = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Quantidade inválida.");
                continue;
            }

            if (quantidade <= 0) {
                System.out.println("Quantidade deve ser maior que zero.");
                continue;
            }

            if (quantidade > livroEncontrado.getEstoque()) {
                System.out.println("Estoque insuficiente.");
                continue;
            }

            // Atualizar estoque
            livroEncontrado.setEstoque(
                    livroEncontrado.getEstoque() - quantidade
            );

            System.out.println("Empréstimo realizado!");
            return idLivro;
        }
    }



}



