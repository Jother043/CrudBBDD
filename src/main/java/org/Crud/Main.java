package org.Crud;

import org.Crud.Class.Users;
import org.Crud.Controllers.UsersBBDD;

import java.sql.SQLException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        int opcion = 0;
        UsersBBDD usersBBDD = new UsersBBDD();

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
                        System.err.println("Error al insertar el usuario " + e.getMessage());
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
                        System.err.println("Error al actualizar el usuario" + e.getMessage());
                        if (e.getErrorCode() == 19) {
                            System.err.println("El nombre o el email ya existe");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Cargando usuarios...");
                    try {
                        for (Users user : usersBBDD.getUsers()) {
                            System.out.println(user);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al consultar los usuarios");
                    }
                    System.out.println("Introduce el id del usuario");
                    int idUsuarioDelete = scanner.nextInt();
                    try {
                        usersBBDD.deleteUser(idUsuarioDelete);
                    } catch (SQLException e) {
                        System.err.println("Error al eliminar el usuario" + e.getMessage());
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