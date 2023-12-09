import java.time.LocalDate;

public class Pessoa {
    private String nome; //Armazena o nome da pessoa
    private LocalDate dataNascimento; // Armazena a data de nascimento 

    // Construindo a classe Pessoa
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome; 
        this.dataNascimento = dataNascimento;
    }

    // Obter o nome da pessoa
    public String getNome() {
        return nome; 
    }

    // Obter a data de nascimento da pessoa
    public LocalDate getDataNascimento() {
        return dataNascimento; 
    }
}