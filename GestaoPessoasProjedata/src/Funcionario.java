import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa {
    private BigDecimal salario; // Armazena o salário do funcionário
    private String funcao; // Armazena a função do funcionário

    // Construtor da classe Funcionario
    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento); 

        this.salario = salario;
        this.funcao = funcao;
    }

    // Obter o salário do funcionário
    public BigDecimal getSalario() {
        return salario; 
    }
    
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    // Obter a função do funcionárioq
    public String getFuncao() {
        return funcao;
    }
}
