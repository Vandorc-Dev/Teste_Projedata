import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Collectors;

public class GestaoPessoas {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        //Insere os funcionarios em uma lista
        inserirFuncionarios(funcionarios);

        System.out.println("IPRIMIR LISTA DE FUNCIONÁRIOS COMPLETA:");
        System.out.println("\n");
        imprimirFuncionarios(funcionarios);
        
        //Remove um funcionario passado como argumento 
        
        removerFuncionario(funcionarios, "João");
        System.out.println("\nO funcionário João foi removido");
        System.out.println("\n");
        
        System.out.println("IPRIMIR LISTA DE FUNCIONÁRIOS EXCLUÍNDO O FUNCIONÁRIO João");
        System.out.println("\n");
        imprimirFuncionarios(funcionarios);

        imprimirFuncionarios(funcionarios);
        
        aumentarSalario(funcionarios);
        System.out.println("IPRIMIR LISTA DE FUNCIONÁRIOS COM AUMENTO DE 10%");
        System.out.println("\n");
        imprimirFuncionarios(funcionarios);
        
        
        
    }

    private static void inserirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        funcionarios.add(new Funcionario("Maria", LocalDate.parse("18/02/2000", formatter),
                new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("12/05/1990", formatter),
                new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("02/05/1961", formatter),
                new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("14/10/1988", formatter),
                new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("05/01/1995", formatter),
                new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("19/11/1999", formatter),
                new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("31/03/1993", formatter),
                new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("08/07/1994", formatter),
                new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("24/05/2003", formatter),
                new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("02/09/1996", formatter),
                new BigDecimal("2799.93"), "Gerente"));
    }

    private static void removerFuncionario(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    //Imprimir lista de funcionarios 
    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
            System.out.println("Salário: " + formatarSalario(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("------------------------");
        }
    }

    //Formatar o salário com separador de milhar como ponto, e decimal como vírgula
    private static String formatarSalario(BigDecimal salario) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols());
        return decimalFormat.format(salario);
    }
    
    //3.4
    private static void aumentarSalario(List<Funcionario> funcionarios) {
        BigDecimal aumentoPercentual = new BigDecimal("1.10");
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(aumentoPercentual)));
    }
    
    
 // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }
    
    //3.6
}



