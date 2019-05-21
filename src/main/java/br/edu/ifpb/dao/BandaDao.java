package br.edu.ifpb.dao;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.factory.PostgreConFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BandaDao {

    private PostgreConFactory factory;

    public BandaDao() {
        factory = new PostgreConFactory();
    }

    public boolean save(Banda banda) {
        String sql = "INSERT INTO banda(localDeOrigem, nomeFantasia) VALUES(?, ?)";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, banda.getLocalDeOrigem());
            st.setString(2, banda.getNomeFantasia());

            return (st.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean delete(Banda banda) {
        String sql = "DELETE FROM banda WHERE id = ?";

        try (Connection connection = factory.gerConnection()) {
            PreparedStatement st =connection.prepareStatement(sql);

            st.setInt(1, banda.getId());

            return (st.executeUpdate() > 0);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Banda findByOrigem(String origem){
        String sql = "SELECT * FROM banda WHERE localDeOrigem = ?";

        try(Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, origem);

            ResultSet result = st.executeQuery();

            return new Banda(result.getInt("id"), result.getString("localDeOrigem"),
                    result.getString("nomeFantasia"));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean update(Banda banda){
        String sql = "UPDATE SET(localDeOrigem, nomeFantasia) = (?, ?) WHERE id = ?";

        try(Connection connection = factory.gerConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, banda.getLocalDeOrigem());
            st.setString(2, banda.getNomeFantasia());
            st.setInt(3, banda.getId());

            return (st.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


}