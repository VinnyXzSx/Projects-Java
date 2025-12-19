package emprestimoslivros;

public class Livro {
    private String titulo;
    private String autor;
    private int estoque;
    private int idLivro;

    public Livro(int idLivro, String titulo, String autor, int estoque){
        this.titulo = titulo;
        this.autor = autor;
        this.idLivro = idLivro;
        this.estoque = estoque;
    }
    //getters
    public String getTitulo(){return titulo;}
    public String getAutor(){return autor;}
    public int getEstoque(){return estoque;}
    public int getIdLivro(){return idLivro;}


    //setter
    public void setEstoque(int qntd){this.estoque -= qntd;}
}
