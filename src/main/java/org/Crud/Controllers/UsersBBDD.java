package org.Crud.Controllers;

import org.Crud.Class.Users;
import org.Crud.Connection.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersBBDD {

    public static Connection getConnection() throws SQLException {

        DataBaseConnection connection;
        connection = DataBaseConnection.getInstance();
        return connection.getConexion();
    }

    public List<Users> getUsers() throws SQLException {
        List<Users> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM usuarios")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new Users(resultSet.getInt("idUsuario"), resultSet.getString("usuario"), resultSet.getString("password"), resultSet.getString("nombre"), resultSet.getString("email")
                ));
            }
        }
        return users;
    }

    /**
     * Check if the user exists in the database.
     *
     * @param users;
     * @return
     * @throws SQLException;
     */
    public boolean userExists(Users users) throws SQLException {
        boolean existName = false;
        boolean existEmail = false;
        for(Users user : getUsers()){
            if(user.getUserName().equals(users.getUserName())){
                existName = true;
            }
        }
        for(Users user : getUsers()){
            if(user.getEmail().equals(users.getEmail())){
                existEmail = true;
            }
        }
        if(existName){
            throw new SQLException("El nombre ya existe");
        }
        if(existEmail){
            throw new SQLException("El email ya existe");
        }
        return false;
    }

    /**
     * Register a user by userName, password, name and email.
     *
     * @param userName;
     * @param password;
     * @param name;
     * @param email;
     * @throws SQLException;
     */
    public void registerUser(String userName, String password, String name, String email) throws SQLException {
        if (userName.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            throw new SQLException("Los campos no pueden estar vacíos");
        }
        if (userExists(new Users(0, userName, password, name, email))) {
            throw new SQLException("El usuario ya existe");
        } else {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO usuarios VALUES (?,?, ?, ?, ?)")) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, userName);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, name);
                preparedStatement.setString(5, email);
                preparedStatement.executeUpdate();
            }
        }
        System.out.println("Usuario registrado correctamente");
    }

    /**
     * Delete a user by userName.
     *
     * @param userName;
     * @throws SQLException;
     */
    public void deleteUser(int userName) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM usuarios WHERE usuario = ?")) {
            preparedStatement.setInt(1, userName);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Update a user by userName, password, name and email.
     *
     * @param userName;
     * @param password;
     * @param name;
     * @param email;
     * @throws SQLException;
     */
    public void updateUser(int idUser, String userName, String password, String name, String email) throws SQLException {
        if (userName.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            throw new SQLException("Los campos no pueden estar vacíos");
        } else {
            try (PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE usuarios SET usuario = ?, password = ?, nombre = ?, email = ? WHERE idUsuario = ?")) {
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, email);
                preparedStatement.setInt(5, idUser);
                preparedStatement.executeUpdate();
            }
        }
        System.out.println("Usuario actualizado correctamente");
    }

    /**
     * Cnsult a user by userName.
     *
     * @param idUsuario;
     * @throws SQLException;
     */
    public void consultUser(int idUsuario) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?")) {
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeQuery();
        }
    }

}