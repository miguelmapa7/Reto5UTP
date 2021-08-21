package model.dao;

//Estructura de datos
import java.util.ArrayList;

import model.vo.Lider;
import model.vo.Proyecto;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import util.JDBCUtilities;

public class ProyectoDao {

    public ArrayList<Proyecto> query_requerimiento_1() throws SQLException {

        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
        Connection conexion = JDBCUtilities.getConnection();

        try {
            String consulta = "SELECT "
                    + "Fecha_Inicio"
                    + ", Numero_Habitaciones "
                    + ", Numero_Banos "
                    + "From "
                    + "Proyecto p "
                    + "WHERE "
                    + "p.Constructora = 'Pegaso'";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Proyecto objetoProyecto = new Proyecto();
                objetoProyecto.setFecha_inicio(resultSet.getString("Fecha_Inicio"));
                objetoProyecto.setNum_habitaciones(resultSet.getInt("Numero_Habitaciones"));
                objetoProyecto.setNum_banios(resultSet.getInt("Numero_Banos"));

                proyectos.add(objetoProyecto);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error en la consulta " + e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }

        return proyectos;

    }// Fin del método query_requerimiento_1

    public ArrayList<Proyecto> query_requerimiento_2() throws SQLException {

        Connection conexion = JDBCUtilities.getConnection();
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();

        try {

            ResultSet resultSet = conexion.createStatement()
                    .executeQuery("SELECT  "
                            + "p.Fecha_Inicio"
                            + ", p.Numero_Habitaciones"
                            + ", p.Numero_Banos"
                            + ", l.Nombre"
                            + ", l.Primer_Apellido"
                            + ", t.Estrato "
                            + "FROM Proyecto p "
                            + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
                            + "INNER JOIN Tipo t ON t.ID_Tipo = p.ID_Tipo "
                            + "WHERE "
                            + "p.Constructora = 'Pegaso' LIMIT 50");

            while (resultSet.next()) {

                Proyecto objetoProyecto = new Proyecto();
                objetoProyecto.setFecha_inicio(resultSet.getString("Fecha_Inicio"));
                objetoProyecto.setNum_habitaciones(resultSet.getInt("Numero_Habitaciones"));
                objetoProyecto.setNum_banios(resultSet.getInt("Numero_Banos"));
                objetoProyecto.setEstrato_proyecto(resultSet.getInt("Estrato"));

                String nombre_lider = resultSet.getString("Nombre");
                String apellido_lider = resultSet.getString("Primer_Apellido");
                Lider objetoLider = new Lider(nombre_lider, apellido_lider);
                objetoProyecto.setLider(objetoLider);

                proyectos.add(objetoProyecto);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return proyectos;
    }// Fin del método query_requerimiento_2

    public ArrayList<Proyecto> query_requerimiento_3() throws SQLException {

        Connection conexion = JDBCUtilities.getConnection();
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();

        try {

            ResultSet resultSet = conexion.createStatement().executeQuery(
                    "SELECT "
                    + "Sum(Numero_Habitaciones) as Total_Habitaciones, p.Constructora "
                    + "FROM "
                    + "Proyecto p "
                    + "Group by p.Constructora");

            while (resultSet.next()) {

                Proyecto objProyecto = new Proyecto();
                objProyecto.setNum_habitaciones(resultSet.getInt("Total_Habitaciones"));
                objProyecto.setNombre_constructora(resultSet.getString("Constructora"));

                proyectos.add(objProyecto);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return proyectos;
    }// Fin del método query_requerimiento_3

    public ArrayList<Lider> query_requerimiento_4() throws SQLException {

        Connection conexion = JDBCUtilities.getConnection();
        ArrayList<Lider> lideres = new ArrayList<Lider>();
        // Consultas
        try {

            ResultSet resultSet = conexion.createStatement().executeQuery(
                    "SELECT "
                    + "l.Nombre"
                    + ", l.Primer_Apellido "
                    + "FROM Proyecto p "
                    + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
                    + "WHERE "
                    + "p.Constructora = 'Pegaso'");

            while (resultSet.next()) {

                Lider objLider = new Lider(resultSet.getString("Nombre")
                        , resultSet.getString("Primer_Apellido"));

                lideres.add(objLider);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return lideres;
    }// Fin del método query_requerimiento_4

    public ArrayList<Proyecto> query_requerimiento_5() throws SQLException {
        
        Connection conexion = JDBCUtilities.getConnection();
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
        
        try {
            
            ResultSet resultSet = conexion.createStatement().executeQuery(
                    "SELECT "
                    + "sum(p.Numero_Habitaciones) as Total_Habitaciones, Constructora "
                    + "FROM Proyecto p "
                    + "GROUP BY Constructora "
                    + "HAVING Total_Habitaciones > 200.0 "
                    + "ORDER BY Total_Habitaciones");
            
            while (resultSet.next()) {
                // Almacena los resultados del query en un objeto Proyecto
                Proyecto objProyecto = new Proyecto();
                objProyecto.setNum_habitaciones(resultSet.getInt("Total_Habitaciones"));
                objProyecto.setNombre_constructora(resultSet.getString("Constructora"));
                
                proyectos.add(objProyecto);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return proyectos;
    }// Fin del método query_requerimiento_5

}
