package org.Crud;

import org.Crud.Class.Users;
import org.Crud.Controllers.UsersBBDD;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UsersBBDD usersBBDD = new UsersBBDD();

        System.out.println("Bienvenido a la aplicación de gestión de usuarios.");
        System.out.println("-------------------------------------------------");
        System.out.println("Creando usuarios predeterminados...");
        //Create five users in the database with the following data:
        Users user1 = new Users(1, "user1", "1234", "Jose", "user1@correo.com");
        Users user2 = new Users(2, "user2", "1234", "Maria", "User2@correo.com");
        Users user3 = new Users(3, "user3", "1234", "Juan", "User3@correo.com");
        Users user4 = new Users(4, "user4", "1234", "Ana", "User4@correo.com");
        Users user5 = new Users(5, "user5", "1234", "Pedro", "User5@correo.com");
        //Add the users to the database
        try {
            usersBBDD.registerUser(user1.getUserName(), user1.getPassword(), user1.getName(), user1.getEmail());
            usersBBDD.registerUser(user2.getUserName(), user2.getPassword(), user2.getName(), user2.getEmail());
            usersBBDD.registerUser(user3.getUserName(), user3.getPassword(), user3.getName(), user3.getEmail());
            usersBBDD.registerUser(user4.getUserName(), user4.getPassword(), user4.getName(), user4.getEmail());
            usersBBDD.registerUser(user5.getUserName(), user5.getPassword(), user5.getName(), user5.getEmail());
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario. " + e.getMessage());
        }

        int opcion = 0;


        do {
            System.out.println(menu());
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el nombre del usuario");
                    String userName = scanner.next();
                    System.out.println("Introduce la contraseña del usuario");
                    String password = scanner.next();
                    System.out.println("Introduce el nombre del usuario");
                    String name = scanner.next();
                    System.out.println("Introduce el email del usuario");
                    String email = scanner.next();
                    try {
                        usersBBDD.registerUser(userName, password, name, email);
                    } catch (SQLException e) {
                        System.err.println("Error al insertar el usuario. " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Cargando usuarios...");
                    try {
                        for (Users user : usersBBDD.getUsers()) {
                            System.out.println(user);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al consultar los usuarios");
                    }
                    break;
                case 3:
                    System.out.println("Cargando usuarios...");
                    try {
                        for (Users user : usersBBDD.getUsers()) {
                            System.out.println(user);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al consultar los usuarios");
                    }
                    System.out.println("Introduce el id del usuario");
                    int idUsuario = scanner.nextInt();
                    System.out.println("Introduce el nombre del usuario");
                    String userNameUpdate = scanner.next();
                    System.out.println("Introduce la contraseña del usuario");
                    String passwordUpdate = scanner.next();
                    System.out.println("Introduce el nombre del usuario");
                    String nameUpdate = scanner.next();
                    System.out.println("Introduce el email del usuario");
                    String emailUpdate = scanner.next();
                    try {
                        usersBBDD.updateUser(idUsuario, userNameUpdate, passwordUpdate, nameUpdate, emailUpdate);
                    } catch (SQLException e) {
                        System.err.println("Error al actualizar el usuario. " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Cargando usuarios...");
                    try {
                        for (Users user : usersBBDD.getUsers()) {
                            System.out.println(user);
                        }

                        System.out.println("Introduce el id del usuario");
                        int idUsuarioDelete = scanner.nextInt();

                        usersBBDD.deleteUser(idUsuarioDelete);
                    } catch (SQLException e) {
                        System.err.println("Error al eliminar el usuario. " + e.getMessage());
                    } catch (InputMismatchException o) {
                        throw new InputMismatchException("El id debe ser un número");
                    }
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (opcion != 5);

    }

    public static String menu() {
        return "1. Insertar usuario\n" +
                "2. Mostrar usuarios\n" +
                "3. Actualizar usuario\n" +
                "4. Eliminar usuario\n" +
                "5. Salir\n" +
                "Selecciona una opción:";

    }
}