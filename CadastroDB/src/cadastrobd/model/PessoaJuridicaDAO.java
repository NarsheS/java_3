package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private ConectorBD conector = new ConectorBD();

    public PessoaJuridica getPessoa(int pessoaId) {
        PessoaJuridica pessoa = null;
        String sql = "SELECT * FROM pessoa p JOIN pessoa_juridica pj ON p.pessoa_id = pj.pessoa_id WHERE pj.pessoa_id = ?";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conector.getPrepared(conn, sql)) {
            stmt.setInt(1, pessoaId);
            ResultSet rs = conector.getSelect(stmt);
            if (rs.next()) {
                pessoa = new PessoaJuridica(
                        rs.getInt("pessoa_id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                );
            }
            conector.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoa;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM pessoa p JOIN pessoa_juridica pj ON p.pessoa_id = pj.pessoa_id";
        try (Connection conn = conector.getConnection();
             PreparedStatement stmt = conector.getPrepared(conn, sql);
             ResultSet rs = conector.getSelect(stmt)) {
            while (rs.next()) {
                pessoas.add(new PessoaJuridica(
                        rs.getInt("pessoa_id"),
                        rs.getString("nome"),
                        rs.getString("logradouro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("cnpj")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica pessoa) {
        String sqlPessoa = "INSERT INTO pessoa (pessoa_id, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlJuridica = "INSERT INTO pessoa_juridica (pessoa_id, cnpj) VALUES (?, ?)";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtJuridica = conector.getPrepared(conn, sqlJuridica)) {

            // Obtém o próximo valor da sequência
            String sqlGetId = "SELECT NEXT VALUE FOR dbo.pessoa_id_seq"; 

            try (PreparedStatement stmtGetId = conector.getPrepared(conn, sqlGetId);
                 ResultSet rs = stmtGetId.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pessoa.setId(id); // Atualiza o ID no objeto PessoaJuridica

                    // Insere na tabela Pessoa
                    stmtPessoa.setInt(1, id);
                    stmtPessoa.setString(2, pessoa.getNome());
                    stmtPessoa.setString(3, pessoa.getLogradouro());
                    stmtPessoa.setString(4, pessoa.getCidade());
                    stmtPessoa.setString(5, pessoa.getEstado());
                    stmtPessoa.setString(6, pessoa.getTelefone());
                    stmtPessoa.setString(7, pessoa.getEmail());
                    stmtPessoa.executeUpdate();

                    // Insere na tabela PessoaJuridica
                    stmtJuridica.setInt(1, id);
                    stmtJuridica.setString(2, pessoa.getCnpj());
                    stmtJuridica.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void alterar(int id, PessoaJuridica pessoa) {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE pessoa_id = ?";
        String sqlJuridica = "UPDATE pessoa_juridica SET cnpj = ? WHERE pessoa_id = ?";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa);
             PreparedStatement stmtJuridica = conector.getPrepared(conn, sqlJuridica)) {
            stmtPessoa.setString(1, pessoa.getNome());
            stmtPessoa.setString(2, pessoa.getLogradouro());
            stmtPessoa.setString(3, pessoa.getCidade());
            stmtPessoa.setString(4, pessoa.getEstado());
            stmtPessoa.setString(5, pessoa.getTelefone());
            stmtPessoa.setString(6, pessoa.getEmail());
            stmtPessoa.setInt(7, id); // Aqui deve ser "pessoa_id"
            stmtPessoa.executeUpdate();

            stmtJuridica.setString(1, pessoa.getCnpj());
            stmtJuridica.setInt(2, pessoa.getId()); // Aqui deve ser "pessoa_id"
            stmtJuridica.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int pessoaId) {
        String sqlPessoa = "DELETE FROM pessoa WHERE pessoa_id = ?";
        String sqlJuridica = "DELETE FROM pessoa_juridica WHERE pessoa_id = ?";

        try (Connection conn = conector.getConnection();
             PreparedStatement stmtJuridica = conector.getPrepared(conn, sqlJuridica);
             PreparedStatement stmtPessoa = conector.getPrepared(conn, sqlPessoa)) {
            stmtJuridica.setInt(1, pessoaId);
            stmtJuridica.executeUpdate();

            stmtPessoa.setInt(1, pessoaId);
            stmtPessoa.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
