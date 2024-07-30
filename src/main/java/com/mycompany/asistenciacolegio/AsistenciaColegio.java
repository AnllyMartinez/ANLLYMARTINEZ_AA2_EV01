package com.mycompany.asistenciacolegio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AsistenciaColegio {
    // conexion con base de datos. 
    public static class BasedatosConexion {
        private static final String URL = "jdbc:mysql://localhost:3306/bdcontrolasistencia";
        private static final String USUARIO = "root"; // Usuario 
        private static final String PASSWORD = ""; 

        public static Connection getConnection() {
            Connection conexion = null;
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }
            return conexion;
        }
    }

    // Clase principal
    public static void main(String[] args) {
        Connection conexion = BasedatosConexion.getConnection();
        Statement statement;
        ResultSet rs;

        try {
            statement = conexion.createStatement();
            // Esta parte es para saber si esta funcionando correctamente, van a ser cambiados posteriormente.
            
            rs = statement.executeQuery("SELECT * FROM estudiante");
            while (rs.next()) {
                // Mostrar los IDEstudiantes para verficar la conexion a la bd.
                
                System.out.println(rs.getString("estudianteId"));
            }
            // Insercion de datos
            
            statement.execute("INSERT INTO `usuario` (`idUsuario`, `nombre`, `horario`, `rol`) VALUES ('0004', 'Jorge Garcia', 'Martes 6:30-12:30', 'Profesor');");
            // Mostrar el dato para saber si se inserto.
            
            rs = statement.executeQuery("SELECT * FROM usuario");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
            // Actualizar dato
            
            statement.execute("UPDATE `usuario` SET `nombre` = 'Diana Gonzales' WHERE `idUsuario` = '0002'");
            System.out.println("Datos actualizados correctamente.");

            // Mostrar el dato actualizado para verificar.
            rs = statement.executeQuery("SELECT * FROM usuario WHERE `idUsuario` = '0002'");
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
            }
           
        } catch (SQLException e) {
            // Mostrar un mensaje de error si alguna consulta falla.
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            try {
                // Verificar si la conexion no está cerrada antes de cerrarla.
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                // Mostrar un mensaje de error si no se puede cerrar la conexion.
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}