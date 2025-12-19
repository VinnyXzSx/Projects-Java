package emprestimoslivros;

import java.time.LocalDate;

public class Registro {

    private Usuario usuario;
    private Livro livro;
    private int quantidade;
    private LocalDate data;

    public Registro(Usuario usuario, Livro livro, int quantidade) {
        this.usuario = usuario;
        this.livro = livro;
        this.quantidade = quantidade;
        this.data = LocalDate.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }
}
