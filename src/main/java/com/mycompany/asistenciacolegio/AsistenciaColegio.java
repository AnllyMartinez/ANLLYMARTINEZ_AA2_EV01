package com.mycompany.asistenciacolegio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AsistenciaColegio {

    public static class BasedatosConexion {
        private static final String URL = "jdbc:mysql://localhost:3306/bdcontrolasistencia";
        private static final String USUARIO = "root"; // Usuario 
        private static final String PASSWORD = ""; 

        public static Connection getConnection() {
            Connection conexion = null;
            Statement statement;
            ResultSet rs;
            try {
                conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                statement = conexion.createStatement();
              //Esta parte es para saber si esta funcionando correctamente, van a ser cambiados posteriormente. 
                rs = statement.executeQuery("SELECT * FROM estudiante");
                 while(rs.next()){
                    System.out.println(rs.getString("estudianteId"));
                }
                // Insercion de datos
                statement.execute("INSERT INTO `usuario` (`idUsuario`, `nombre`, `horario`, `rol`) VALUES ('0002', 'Nicolas', 'Lunes 8:30-16:30', 'Estudiante');");
                // Mostrar el dato para saber si se inserto. 
                rs = statement.executeQuery("SELECT * FROM usuario");
                while(rs.next()){
                    System.out.println(rs.getString("nombre"));
                }
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            }
            return conexion;
        }
    }

    // Clase principal
    public static void main(String[] args) {
        // Llamar al método getConnection() de la clase BasedatosConexion
        BasedatosConexion.getConnection();
    }
}
