import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GestaoPessoas {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        inserirFuncionarios(funcionarios);

        removerFuncionario(funcionarios, "João");

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

    private static String formatarSalario(BigDecimal salario) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols());
        return decimalFormat.format(salario);
    }
}