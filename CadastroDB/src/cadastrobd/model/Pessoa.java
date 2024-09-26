package cadastrobd.model;

public class Pessoa {
    private int id;
    private String nome;
    private String logradouro;
    private String cidade;
    private String estado;
    private String telefone;
    private String email;

    // Construtor padrão
    public Pessoa() {}

    // Construtor completo
    public Pessoa(int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }
    
    
    // Métodos set
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) { this.email = email; }

    // Métodos get
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getLogradouro() { return logradouro; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    
    
    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: "+ nome);
        System.out.println("Logradouro: "+ logradouro);
        System.out.println("Cidade: "+ cidade);
        System.out.println("Estado: "+ estado);
        System.out.println("Telefone: "+ telefone);
        System.out.println("Email: "+ email);
    }
}
