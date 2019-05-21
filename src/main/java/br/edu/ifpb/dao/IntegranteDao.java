package br.edu.ifpb.dao;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.factory.PostgreConFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegranteDao {

    private PostgreConFactory factory;

    public IntegranteDao() {
        factory = new PostgreConFactory();
    }

    public boolean save(Integrante integrante) {
        String sql = "INSERT INTO integrante(nome, dataNascimento, cpf) VALUES (?, ?, ?)";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, integrante.getNome());
            st.setDate(2, integrante.getDataDeNascimento());
            st.setString(3, integrante.getCpf().valor());

            return (st.executeUpdate() > 0);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(Integrante integrante) {
        String sql = "DELETE FROM integrante WHERE id = ?";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, integrante.getId());

            return (st.executeUpdate() > 0);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Integrante findByCpf(CPF cpf) {
        String sql = "SELECT * FROM integrante WHERE cpf = ?";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection
                    .prepareStatement(sql);

            st.setString(1, cpf.valor());
            ResultSet result = st.executeQuery();

            return new Integrante(result.getInt("id"), result.getString("nome"),
                    result.getDate("dataDeNascimento").toLocalDate(), new CPF(result.getString("cpf")));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Integrante integrante) {
        String sql = "UPDATE integrante SET(nome, dataDeNascimento, cpf) = (?, ?, ?) WHERE id = ?";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, integrante.getNome());
            st.setDate(2, integrante.getDataDeNascimento());
            st.setString(3, integrante.getCpf().valor());

            return (st.executeUpdate() > 0);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}