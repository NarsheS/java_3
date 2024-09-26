package cadastrobd;

import cadastrobd.model.*;
import java.util.Scanner;
import java.util.List;

public class CadastroDB {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        PessoaFisicaDAO fisicos = new PessoaFisicaDAO();
        PessoaJuridicaDAO juridicos = new PessoaJuridicaDAO();
        
        while (true) {
            System.out.println("================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("================================");
            
            String inputVal = scan.nextLine();
            
            switch(inputVal){
                case "1":
                    incluirPessoa(scan, fisicos, juridicos);
                    break;
                case "2":
                    alterarPessoa(scan, fisicos, juridicos);
                    break;
                case "3":
                    excluirPessoa(scan, fisicos, juridicos);
                    break;
                case "4":
                    buscarPessoa(scan, fisicos, juridicos);
                    break;
                case "5":
                    exibirTodos(scan, fisicos, juridicos);
                    break;
                case "0":
                    System.out.println("Finalizando o programa...");
                    scan.close();
                    return;
                default:
                    System.out.println("Opção Invalida. Tente novamente.");
            }
            
            
        }
        
       
    }
    
    private static void incluirPessoa(Scanner scan, PessoaFisicaDAO fisicos, PessoaJuridicaDAO juridicos) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String incluirTipoPessoa = scan.nextLine().toUpperCase();
                
                
        if (incluirTipoPessoa.equals("F") || incluirTipoPessoa.equals("J")) {
            System.out.println("Insira os dados...");
            System.out.println("Nome: ");
            String nome = scan.nextLine();
            System.out.println("Logradouro: ");
            String logradouro = scan.nextLine();
            System.out.println("Cidade: ");
            String cidade = scan.nextLine();
            System.out.println("Estado: ");
            String estado = scan.nextLine();
            System.out.println("Telefone: ");
            String telefone = scan.nextLine();
            System.out.println("Email: ");
            String email = scan.nextLine();
            try {
                if (incluirTipoPessoa.equals("F")) {
                    System.out.println("CPF: ");
                    String cpf = scan.nextLine();
                    PessoaFisica pf = new PessoaFisica(0, nome, logradouro, cidade, estado, telefone, email, cpf);
                    fisicos.incluir(pf);
                    System.out.println("Incluido com sucesso!");
                } else if (incluirTipoPessoa.equals("J")) {
                    System.out.println("CNPJ: ");
                    String cnpj = scan.nextLine();
                    PessoaJuridica pj = new PessoaJuridica(0, nome, logradouro, cidade, estado, telefone, email, cnpj);
                    juridicos.incluir(pj);
                    System.out.println("Incluido com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao cadastrar ID ou Idade, favor usar numeros!");
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
    
    private static void alterarPessoa(Scanner scan, PessoaFisicaDAO fisicos, PessoaJuridicaDAO juridicos) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String alterarTipoPessoa = scan.nextLine().toUpperCase();
        
        if (alterarTipoPessoa.equals("F") || alterarTipoPessoa.equals("J")) {
            System.out.println("Informe o Id da pessoa: ");
            int numId = Integer.parseInt(scan.nextLine());
            System.out.println("Insira os dados...");
            System.out.println("Nome: ");
            String nome = scan.nextLine();
            System.out.println("Logradouro: ");
            String logradouro = scan.nextLine();
            System.out.println("Cidade: ");
            String cidade = scan.nextLine();
            System.out.println("Estado: ");
            String estado = scan.nextLine();
            System.out.println("Telefone: ");
            String telefone = scan.nextLine();
            System.out.println("Email: ");
            String email = scan.nextLine();
            
            try {
                if (alterarTipoPessoa.equals("F")) {
                    System.out.println("CPF: ");
                    String cpf = scan.nextLine();
                    PessoaFisica newPf = new PessoaFisica(numId, nome, logradouro, cidade, estado, telefone, email, cpf);
                    fisicos.alterar(numId, newPf);
                    System.out.println("Alterado com sucesso!");
                } else if (alterarTipoPessoa.equals("J")) {
                    System.out.println("CNPJ: ");
                    String cnpj = scan.nextLine();
                    PessoaJuridica newPj = new PessoaJuridica(numId, nome, logradouro, cidade, estado, telefone, email, cnpj);
                    juridicos.alterar(numId, newPj);
                    System.out.println("Alterado com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
    private static void excluirPessoa(Scanner scan, PessoaFisicaDAO fisicos, PessoaJuridicaDAO juridicos) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String excluirTipoPessoa = scan.nextLine().toUpperCase();
        
        if (excluirTipoPessoa.equals("F") || excluirTipoPessoa.equals("J")) {
            System.out.println("Informe o Id: ");
            int excluirPessoa = Integer.parseInt(scan.nextLine());
            
            try {
                if (excluirTipoPessoa.equals("F")) {
                    fisicos.excluir(excluirPessoa);
                    System.out.println("Excluido com sucesso!");
                } else if (excluirTipoPessoa.equals("J")) {
                    juridicos.excluir(excluirPessoa);
                    System.out.println("Excluido com sucesso!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: O Id deve ser um numero\n" + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }

    private static void buscarPessoa(Scanner scan, PessoaFisicaDAO fisicos, PessoaJuridicaDAO juridicos) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String obterTipoPessoa = scan.nextLine().toUpperCase();
        
        if (obterTipoPessoa.equals("F") || obterTipoPessoa.equals("J")) {
            System.out.println("Informe o Id: ");
            int obterPessoa = Integer.parseInt(scan.nextLine());
            try {
                if (obterTipoPessoa.equals("F")) {
                    PessoaFisica pf = fisicos.getPessoa(obterPessoa);
                    if (pf != null) {
                        pf.exibir();
                    } else {
                        System.out.println("Pessoa Física nao encontrada.");
                    }
                } else if (obterTipoPessoa.equals("J")) {
                    PessoaJuridica pj = juridicos.getPessoa(obterPessoa);
                    if (pj != null) {
                        pj.exibir();
                    } else {
                        System.out.println("Pessoa Juridica nao encontrada.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: O Id deve ser um número\n" + e.getMessage());
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }

    private static void exibirTodos(Scanner scan, PessoaFisicaDAO fisicos, PessoaJuridicaDAO juridicos) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        String obterTodosTipo = scan.nextLine().toUpperCase();
        if (obterTodosTipo.equals("F") || obterTodosTipo.equals("J")) {
            if (obterTodosTipo.equals("F")) {
                List<PessoaFisica> todasPessoasFisicas = fisicos.getPessoas();
                for (PessoaFisica pf : todasPessoasFisicas) {
                    pf.exibir();
                }
            } else if (obterTodosTipo.equals("J")) {
                List<PessoaJuridica> todasPessoasJuridicas = juridicos.getPessoas();
                for (PessoaJuridica pj : todasPessoasJuridicas) {
                    pj.exibir();
                }
            }
        } else {
            System.out.println("Tipo Invalido. Por favor selecione com 'F' ou 'J'.");
        }
    }
    
}
