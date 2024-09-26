package cadastrobd.model;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        // Instanciar e persistir uma pessoa física
        PessoaFisica pessoaFisica = new PessoaFisica(0, "João Silva", "Rua A", "Cidade X", "ES", "123456789", "joao@email.com", "12345678910");
        PessoaFisicaDAO fisicaDAO = new PessoaFisicaDAO();
        //fisicaDAO.incluir(pessoaFisica);

        // Alterar dados da pessoa física
        pessoaFisica.setNome("João Silva Alterado");
        fisicaDAO.alterar(pessoaFisica.getId(), pessoaFisica);

        // Consultar e listar todas as pessoas físicas
        List<PessoaFisica> fisicas = fisicaDAO.getPessoas();
        for (PessoaFisica pf : fisicas) {
            pf.exibir();
        }
        

        // Excluir a pessoa física
        fisicaDAO.excluir(pessoaFisica.getId());

        // Instanciar e persistir uma pessoa jurídica
        PessoaJuridica pessoaJuridica = new PessoaJuridica(0, "Empresa X", "Avenida B", "Cidade Z", "RS", "987654321", "empresa@email.com", "44444444444413");
        PessoaJuridicaDAO juridicaDAO = new PessoaJuridicaDAO();
        juridicaDAO.incluir(pessoaJuridica);

        // Alterar dados da pessoa jurídica
        pessoaJuridica.setNome("Empresa X Alterada");
        juridicaDAO.alterar(pessoaJuridica.getId(), pessoaJuridica);

        // Consultar e listar todas as pessoas jurídicas
        List<PessoaJuridica> juridicas = juridicaDAO.getPessoas();
        for (PessoaJuridica pj : juridicas) {
            pj.exibir();
        }

        // Excluir a pessoa jurídica
        juridicaDAO.excluir(pessoaJuridica.getId());
    }
}
