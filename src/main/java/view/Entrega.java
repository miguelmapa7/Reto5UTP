/////*
//// * To change this license header, choose License Headers in Project Properties.
//// * To change this template file, choose Tools | Templates
//// * and open the template in the editor.
//// */
////package view;
////
////import controller.Controlador;
////
////import java.io.File;//Para verificación de longitud de base de datos
////import java.sql.DriverManager;
////
////
////Estructura de datos
////import java.util.ArrayList;
////
////import model.vo.Lider;
////import model.vo.Proyecto;
////
////Librerías para SQL y Base de Datos
////import java.sql.SQLException;
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////
////Clase para conexión
////import util.JDBCUtilities;
////
////
////
////public class Lider {
////
////    /**
////     * ***********
////     * Atributos ***********
////     */
////    private String nombre;
////    private String apellido;
////
////    Constructor
////    public Lider(String nombre, String apellido) {
////        this.nombre = nombre;
////        this.apellido = apellido;
////    }
////
////    Consultores y Modificadores
////    public String getNombre() {
////        return nombre;
////    }
////
////    public void setNombre(String nombre) {
////        this.nombre = nombre;
////    }
////
////    public String getApellido() {
////        return apellido;
////    }
////
////    public void setApellido(String apellido) {
////        this.apellido = apellido;
////    }
////}
////
////public class Proyecto {
////
////    private String fecha_inicio;
////    private int num_habitaciones;
////    private int num_banios;
////    private String nombre_constructora;
////    private int estrato_proyecto;
////    private model.vo.Lider lider;
////
////    public Proyecto() {
////
////    }
////
////    /**
////     * *****************************
////     * Consultores y modificadores
////     *******************************
////     */
////    public String getFecha_inicio() {
////        return fecha_inicio;
////    }
////
////    public void setFecha_inicio(String fecha_inicio) {
////        this.fecha_inicio = fecha_inicio;
////    }
////
////    public int getNum_habitaciones() {
////        return num_habitaciones;
////    }
////
////    public void setNum_habitaciones(int num_habitaciones) {
////        this.num_habitaciones = num_habitaciones;
////    }
////
////    public int getNum_banios() {
////        return num_banios;
////    }
////
////    public void setNum_banios(int num_banios) {
////        this.num_banios = num_banios;
////    }
////
////    public String getNombre_constructora() {
////        return nombre_constructora;
////    }
////
////    public void setNombre_constructora(String nombre_constructora) {
////        this.nombre_constructora = nombre_constructora;
////    }
////
////    public model.vo.Lider getLider() {
////        return lider;
////    }
////
////    public void setLider(model.vo.Lider lider) {
////        this.lider = lider;
////    }
////
////    public int getEstrato_proyecto() {
////        return estrato_proyecto;
////    }
////
////    public void setEstrato_proyecto(int estrato_proyecto) {
////        this.estrato_proyecto = estrato_proyecto;
////    }
////}
////
////public class LiderDao {
////
////    public ArrayList<Lider> query_requerimiento_4() throws SQLException {
////        Connection conexion = JDBCUtilities.getConnection();
////         Crea arreglo para almacenar objetos tipo Proyecto
////        ArrayList<Lider> lideres = new ArrayList<Lider>();
////
////        return lideres;
////    }// Fin del método query_requerimiento_4
////}
////
////public class ProyectoDao {
////
////    public ArrayList<Proyecto> query_requerimiento_1() throws SQLException {
////
////        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
////        Connection conexion = JDBCUtilities.getConnection();
////
////        try {
////            String consulta = "SELECT "
////                    + "Fecha_Inicio"
////                    + ", Numero_Habitaciones "
////                    + ", Numero_Banos "
////                    + "From "
////                    + "Proyecto p "
////                    + "WHERE "
////                    + "p.Constructora = 'Pegaso'";
////
////            PreparedStatement statement = conexion.prepareStatement(consulta);
////            ResultSet resultSet = statement.executeQuery();
////
////            while (resultSet.next()) {
////
////                Proyecto objetoProyecto = new Proyecto();
////                objetoProyecto.setFecha_inicio(resultSet.getString("Fecha_Inicio"));
////                objetoProyecto.setNum_habitaciones(resultSet.getInt("Numero_Habitaciones"));
////                objetoProyecto.setNum_banios(resultSet.getInt("Numero_Banos"));
////
////                proyectos.add(objetoProyecto);
////            }
////
////            resultSet.close();
////            statement.close();
////
////        } catch (SQLException e) {
////            System.err.println("Error en la consulta " + e);
////        } finally {
////            if (conexion != null) {
////                conexion.close();
////            }
////        }
////
////        return proyectos;
////
////    }// Fin del método query_requerimiento_1
////
////    public ArrayList<Proyecto> query_requerimiento_2() throws SQLException {
////
////        Connection conexion = JDBCUtilities.getConnection();
////        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
////
////        try {
////
////            ResultSet resultSet = conexion.createStatement()
////                    .executeQuery("SELECT  "
////                            + "p.Fecha_Inicio"
////                            + ", p.Numero_Habitaciones"
////                            + ", p.Numero_Banos"
////                            + ", l.Nombre"
////                            + ", l.Primer_Apellido"
////                            + ", t.Estrato "
////                            + "FROM Proyecto p "
////                            + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
////                            + "INNER JOIN Tipo t ON t.ID_Tipo = p.ID_Tipo "
////                            + "WHERE "
////                            + "p.Constructora = 'Pegaso' LIMIT 50");
////
////            while (resultSet.next()) {
////
////                Proyecto objetoProyecto = new Proyecto();
////                objetoProyecto.setFecha_inicio(resultSet.getString("Fecha_Inicio"));
////                objetoProyecto.setNum_habitaciones(resultSet.getInt("Numero_Habitaciones"));
////                objetoProyecto.setNum_banios(resultSet.getInt("Numero_Banos"));
////                objetoProyecto.setEstrato_proyecto(resultSet.getInt("Estrato"));
////
////                String nombre_lider = resultSet.getString("Nombre");
////                String apellido_lider = resultSet.getString("Primer_Apellido");
////                Lider objetoLider = new Lider(nombre_lider, apellido_lider);
////                objetoProyecto.setLider(objetoLider);
////
////                proyectos.add(objetoProyecto);
////            }
////        } catch (Exception e) {
////             TODO: handle exception
////            System.out.println(e);
////        }
////
////        return proyectos;
////    }// Fin del método query_requerimiento_2
////
////    public ArrayList<Proyecto> query_requerimiento_3() throws SQLException {
////
////        Connection conexion = JDBCUtilities.getConnection();
////        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
////
////        try {
////
////            ResultSet resultSet = conexion.createStatement().executeQuery(
////                    "SELECT "
////                    + "Sum(Numero_Habitaciones) as Total_Habitaciones, p.Constructora "
////                    + "FROM "
////                    + "Proyecto p "
////                    + "Group by p.Constructora");
////
////            while (resultSet.next()) {
////
////                Proyecto objProyecto = new Proyecto();
////                objProyecto.setNum_habitaciones(resultSet.getInt("Total_Habitaciones"));
////                objProyecto.setNombre_constructora(resultSet.getString("Constructora"));
////
////                proyectos.add(objProyecto);
////            }
////        } catch (Exception e) {
////             TODO: handle exception
////            System.out.println(e);
////        }
////
////        return proyectos;
////    }// Fin del método query_requerimiento_3
////
////    public ArrayList<Lider> query_requerimiento_4() throws SQLException {
////
////        Connection conexion = JDBCUtilities.getConnection();
////        ArrayList<Lider> lideres = new ArrayList<Lider>();
////         Consultas
////        try {
////
////            ResultSet resultSet = conexion.createStatement().executeQuery(
////                    "SELECT "
////                    + "l.Nombre"
////                    + ", l.Primer_Apellido "
////                    + "FROM Proyecto p "
////                    + "INNER JOIN Lider l ON l.ID_Lider = p.ID_Lider "
////                    + "WHERE "
////                    + "p.Constructora = 'Pegaso'");
////
////            while (resultSet.next()) {
////
////                Lider objLider = new Lider(resultSet.getString("Nombre")
////                        , resultSet.getString("Primer_Apellido"));
////
////                lideres.add(objLider);
////            }
////        } catch (Exception e) {
////             TODO: handle exception
////            System.out.println(e);
////        }
////
////        return lideres;
////    }// Fin del método query_requerimiento_4
////
////    public ArrayList<Proyecto> query_requerimiento_5() throws SQLException {
////        
////        Connection conexion = JDBCUtilities.getConnection();
////        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
////        
////        try {
////            
////            ResultSet resultSet = conexion.createStatement().executeQuery(
////                    "SELECT "
////                    + "sum(p.Numero_Habitaciones) as Total_Habitaciones, Constructora "
////                    + "FROM Proyecto p "
////                    + "GROUP BY Constructora "
////                    + "HAVING Total_Habitaciones > 200.0 "
////                    + "ORDER BY Total_Habitaciones");
////            
////            while (resultSet.next()) {
////                 Almacena los resultados del query en un objeto Proyecto
////                Proyecto objProyecto = new Proyecto();
////                objProyecto.setNum_habitaciones(resultSet.getInt("Total_Habitaciones"));
////                objProyecto.setNombre_constructora(resultSet.getString("Constructora"));
////                
////                proyectos.add(objProyecto);
////            }
////        } catch (Exception e) {
////             TODO: handle exception
////            System.out.println(e);
////        }
////
////        return proyectos;
////    }// Fin del método query_requerimiento_5
////
////}
////
////public class Controlador {
////
////    private final ProyectoDao proyectoDao;
////    private final LiderDao liderDao;
////
////    public Controlador() {
////        this.proyectoDao = new ProyectoDao();
////        this.liderDao = new LiderDao();
////    }
////
////
////    public ArrayList<Proyecto> Solucionar_requerimiento_1() throws SQLException {
////        return this.proyectoDao.query_requerimiento_1();
////    }
////
////    public ArrayList<Proyecto> Solucionar_requerimiento_2() throws SQLException {
////        return this.proyectoDao.query_requerimiento_2();
////    }
////
////    public ArrayList<Proyecto> Solucionar_requerimiento_3() throws SQLException {
////        return this.proyectoDao.query_requerimiento_3();
////    }
////
////    public ArrayList<Lider> Solucionar_requerimiento_4() throws SQLException {
////       return this.proyectoDao.query_requerimiento_4();
////    }
////
////    public ArrayList<Proyecto> Solucionar_requerimiento_5() throws SQLException {
////        return this.proyectoDao.query_requerimiento_5();
////    }
////
////}
////
////public class JDBCUtilities {
////
////     Atributos de clase para gestión de conexión con la base de datos
////    private static final String UBICACION_BD = "ProyectosConstruccion.db";
////
////    public static Connection getConnection() throws SQLException {
////        String url = "jdbc:sqlite:" + UBICACION_BD;
////        return DriverManager.getConnection(url);
////    }
////
////    public static boolean estaVacia() {
////        File archivo = new File(JDBCUtilities.UBICACION_BD);
////        return archivo.length() == 0;
////    }
////
////}
////
////public class Vista {
////
////    public static final Controlador controlador = new Controlador();
////
////    public static void vista_requerimiento_1() {
////
////        try {
////
////            ArrayList<Proyecto> proyectos = controlador.Solucionar_requerimiento_1();
////            for (int i = 0; i < proyectos.size(); i++) {
////                String resultado = "Fecha_Inicio: " + proyectos.get(i).getFecha_inicio();
////                resultado += " - Numero_Habitaciones: " + proyectos.get(i).getNum_habitaciones();
////                resultado += " - Numero_Banos: " + proyectos.get(i).getNum_banios();
////                System.out.println(resultado);
////            }
////
////        } catch (SQLException e) {
////            System.err.println("Ha ocurrido un error!" + e.getMessage());
////        }
////
////    }
////
////    public static void vista_requerimiento_2() {
////        try {
////
////            ArrayList<Proyecto> proyectos = controlador.Solucionar_requerimiento_2();
////            for (int i = 0; i < proyectos.size(); i++) {
////                String resultado = "Fecha_Inicio: " + proyectos.get(i).getFecha_inicio();
////                resultado += " - Numero_Habitaciones: " + proyectos.get(i).getNum_habitaciones();
////                resultado += " - Numero_Banos: " + proyectos.get(i).getNum_banios();
////                resultado += " - Nombre_Lider: " + proyectos.get(i).getLider().getNombre();
////                resultado += " - Apellido_Lider: " + proyectos.get(i).getLider().getApellido();
////                resultado += " - Estrato_Proyecto: " + proyectos.get(i).getEstrato_proyecto();
////                System.out.println(resultado);
////            }
////
////        } catch (SQLException e) {
////            System.err.println("Ha ocurrido un error!" + e.getMessage());
////        }
////    }
////
////    public static void vista_requerimiento_3() {
////        try {
////
////            ArrayList<Proyecto> proyectos = controlador.Solucionar_requerimiento_3();
////            for (int i = 0; i < proyectos.size(); i++) {
////                String resultado = "Total_Habitaciones: " + proyectos.get(i).getNum_habitaciones();
////                resultado += " - Constructora: " + proyectos.get(i).getNombre_constructora();
////                System.out.println(resultado);
////            }
////
////        } catch (SQLException e) {
////            System.err.println("Ha ocurrido un error!" + e.getMessage());
////        }
////    }
////
////    public static void vista_requerimiento_4() {
////        try {
////
////            ArrayList<Lider> lideres = controlador.Solucionar_requerimiento_4();
////            for (int i = 0; i < lideres.size(); i++) {
////                String resultado = "Nombre_Lider: " 
////                        + lideres.get(i).getNombre() 
////                        + " - Apellido_Lider: "
////                        + lideres.get(i).getApellido();
////                System.out.println(resultado);
////            }
////
////        } catch (SQLException e) {
////            System.err.println("Ha ocurrido un error!" + e.getMessage());
////        }
////    }
////
////    public static void vista_requerimiento_5() {
////        try {
////
////            ArrayList<Proyecto> proyectos = controlador.Solucionar_requerimiento_5();
////            for (int i = 0; i < proyectos.size(); i++) {
////                String resultado = "Total_Habitaciones: " + proyectos.get(i).getNum_habitaciones();
////                resultado += " - Constructora: " + proyectos.get(i).getNombre_constructora();
////                System.out.println(resultado);
////            }
////
////        } catch (SQLException e) {
////            System.err.println("Ha ocurrido un error!" + e.getMessage());
////        }
////    }
////
////}


