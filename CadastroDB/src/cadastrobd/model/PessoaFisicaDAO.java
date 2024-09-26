package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDAO {
    private ConectorBD conector = new ConectorBD();

    public PessoaFisica getPessoa(int pessoaId) {
        PessoaFisica pessoa = null;
        String sql = "SELECT * FROM pessoa p JOIN pessoa_fisica pf ON p.pessoa_id = pf.pessoa_id WHERE pf.pessoa_id = ?";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conector.getPrepared(conn, sql)) {
            stmt.setInt(1, pessoaId);
            ResultSet rs = conector.getSelect(stmt);
            if (rs.next()) {
                pessoa = new PessoaFisica(
                        rs.getInt("pessoa_id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                );
            }
            conector.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa p JOIN pessoa_fisica pf ON p.pessoa_id = pf.pessoa_id";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conector.getPrepared(conn, sql);
             ResultSet rs = conector.getSelect(stmt)) {
            while (rs.next()) {
                pessoas.add(new PessoaFisica(
                        rs.getInt("pessoa_id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoa) {
        String sqlPessoa = "INSERT INTO pessoa (pessoa_id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlFisica = "INSERT INTO pessoa_fisica (pessoa_id, cpf) VALUES (?, ?)";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtFisica = conector.getPrepared(conn, sqlFisica)) {

            // Obtém o próximo valor da sequência
            String sqlGetId = "SELECT NEXT VALUE FOR dbo.pessoa_id_seq"; 

            try (PreparedStatement stmtGetId = conector.getPrepared(conn, sqlGetId);
                 ResultSet rs = stmtGetId.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pessoa.setId(id); // Atualiza o ID no objeto PessoaFisica

                    // Insere na tabela Pessoa
                    stmtPessoa.setInt(1, id);
                    stmtPessoa.setString(2, pessoa.getNome());
                    stmtPessoa.setString(3, pessoa.getLogradouro());
                    stmtPessoa.setString(4, pessoa.getCidade());
                    stmtPessoa.setString(5, pessoa.getEstado());
                    stmtPessoa.setString(6, pessoa.getTelefone());
                    stmtPessoa.setString(7, pessoa.getEmail());
                    stmtPessoa.executeUpdate();

                    // Insere na tabela PessoaFisica
                    stmtFisica.setInt(1, id);
                    stmtFisica.setString(2, pessoa.getCpf());
                    stmtFisica.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void alterar(int id, PessoaFisica pessoa) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE pessoa_id = ?";
        String sqlFisica = "UPDATE pessoa_fisica SET cpf = ? WHERE pessoa_id = ?";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtFisica = conector.getPrepared(conn, sqlFisica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.setInt(7, id); // Aqui deve ser "pessoa_id"
            stmtPessoa.executeUpdate();

            stmtFisica.setString(1, pessoa.getCpf());
            stmtFisica.setInt(2, pessoa.getId()); // Aqui deve ser "pessoa_id"
            stmtFisica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int pessoaId) {
        String sqlPessoa = "DELETE FROM pessoa WHERE pessoa_id = ?";
        String sqlFisica = "DELETE FROM pessoa_fisica WHERE pessoa_id = ?";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtFisica = conector.getPrepared(conn, sqlFisica);
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa)) {
            stmtFisica.setInt(1, pessoaId);
            stmtFisica.executeUpdate();

            stmtPessoa.setInt(1, pessoaId);
            stmtPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
