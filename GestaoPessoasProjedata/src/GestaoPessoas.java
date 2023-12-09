import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.stream.Collectors;
import java.time.Month;
import java.math.RoundingMode;

public class GestaoPessoas {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        //Insere os funcionarios em uma listas
        inserirFuncionarios(funcionarios);

        System.out.println("IPRIMIR LISTA DE FUNCIONÁRIOS COMPLETA:");
        imprimirFuncionarios(funcionarios);
        
        //Remove um funcionario passado como argumento 
        
        removerFuncionario(funcionarios, "João");
        System.out.println("\nO funcionário João foi removido");
        
        System.out.println("\nIPRIMIR LISTA DE FUNCIONÁRIOS EXCLUÍNDO O FUNCIONÁRIO João");
        imprimirFuncionarios(funcionarios);

        imprimirFuncionarios(funcionarios);
        
        aumentarSalario(funcionarios);
        System.out.println("\nIPRIMIR LISTA DE FUNCIONÁRIOS COM AUMENTO DE 10%");
        imprimirFuncionarios(funcionarios);
        
        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosAgrupados = agruparFuncionariosPorFuncao(funcionarios);

        // 3.6 - Imprimir os funcionários, agrupados por função
        System.out.println("\nIMPRIMIR FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO:");
        imprimirFuncionariosAgrupados(funcionariosAgrupados);
        
        //Imprimir aniversariantes        
        System.out.println("\nIMPRIMIR ANIVERSARIANTES NO MÊS 10 E 12:");
        imprimirAniversariantes(funcionarios);
        
        //Imprimir o funcionário com maior idade
        System.out.println("\nFUNCIONÁRIOS COM MAIOR IDADE:");
        imprimirFuncionarioMaiorIdade(funcionarios);
        
        //Imprimi os funcionários em ordem alfabética
        System.out.println("\nFUNCIONÁRIO, EM ORDEM ALFABÉTICA:");
        imprimirFuncionariosOrdemAlfabetica(funcionarios);
        
        //Total de salarios
        System.out.println("\n TOTAL DE SALÁRIOS DOS FUNCIONÁRIOS:");
        imprimirTotalSalarios(funcionarios);
       
        
        //Salários minimos por funcionário
        System.out.println("\nSALÁRIOS MÍNIMOS POR FUNCIONÁRIO:");
        imprimirSalariosMinimos(funcionarios);
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
    
    
    
    // 3.5 - Agrupar os funcionários por função em um MAP
    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    // 3.6 - Imprimir os funcionários, agrupados por função
    private static void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosAgrupados) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosAgrupados.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> funcionarios = entry.getValue();

            System.out.println("Função: " + funcao);
            System.out.println("------------------------");

            for (Funcionario funcionario : funcionarios) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatter));
                System.out.println("Salário: " + formatarSalario(funcionario.getSalario()));
                System.out.println("------------------------");
            }
        }
    }
    
    //3.8 Aniversariantes no mes 10 e 12
    private static void imprimirAniversariantes(List<Funcionario> funcionarios) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.now();

        for (Funcionario funcionario : funcionarios) {
            LocalDate dataNascimento = funcionario.getDataNascimento();
            Month mesNascimento = dataNascimento.getMonth();
            if (mesNascimento == Month.OCTOBER || mesNascimento == Month.DECEMBER) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + dataNascimento.format(formatter));
                System.out.println("Salário: " + funcionario.getSalario());
                System.out.println("Função: " + funcionario.getFuncao());
                System.out.println("------------------------");
            }
        }
    }
    
    //3.9 Funcionário com maior idade
    private static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
        LocalDate dataAtual = LocalDate.now();
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(funcionario -> calcularIdade(funcionario.getDataNascimento(), dataAtual)))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            int idade = calcularIdade(funcionarioMaisVelho.getDataNascimento(), dataAtual);
            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade);
        }
    }

    private static int calcularIdade(LocalDate dataNascimento, LocalDate dataAtual) {
        return dataAtual.getYear() - dataNascimento.getYear();
    }
    
    
    //3.10 Listar funcionário em ordem alfabética
    private static void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(funcionario -> {
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("Idade: " + calcularIdade(funcionario.getDataNascimento(), LocalDate.now()));
                    System.out.println("Salário: " + funcionario.getSalario());
                    System.out.println("Função: " + funcionario.getFuncao());
                    System.out.println("------------------------");
                });
    }
    
    
    //3.11 Total de salários
    private static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        String totalSalariosFormatado = decimalFormat.format(totalSalarios);

        System.out.println("Total dos salários: R$ " + totalSalariosFormatado);
    }
    
    //3.12 Calcula salários minimos por funcionário
    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal salario = funcionario.getSalario();
            BigDecimal quantidadeSalariosMinimos = salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salário: R$ " + salario);
            System.out.println("Quantidade de salários mínimos: " + quantidadeSalariosMinimos);
            System.out.println("------------------------");
        }
    }

}



