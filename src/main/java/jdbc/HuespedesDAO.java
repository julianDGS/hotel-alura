package jdbc;

import domain.Huespedes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class HuespedesDAO {
    
    private static final String INSERT = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva) VALUES (?, ?, ?, ?,?,?)";
    private static final String SELECT = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva FROM huespedes";
    private static final String BUSCAR_ID = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva FROM huespedes WHERE idReserva = ?";
    private static final String UPDATE = "UPDATE huespedes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, nacionalidad = ?, telefono = ?, idReserva = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM huespedes WHERE id = ?";
    
    private Connection conn;
    
    public HuespedesDAO() {

    }

    public HuespedesDAO(Connection conn) {
        this.conn = conn;
    }
    
    public int guardar(Huespedes huesped) {
	Connection conn = null;
        PreparedStatement stmtt = null;
        int registros = 0;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(INSERT);
            stmtt.setString(1, huesped.getNombre());
            stmtt.setString(2, huesped.getApellido());
            stmtt.setDate(3, huesped.getFechaNacimiento());
            stmtt.setString(4, huesped.getNacionalidad());
            stmtt.setString(5, huesped.getTelefono());
            stmtt.setInt(6, huesped.getIdReserva());

            registros = stmtt.executeUpdate();

//                    try (ResultSet rs = stmtt.getGeneratedKeys()) {
//                        while (rs.next()) {
//                                huesped.setId(rs.getInt(1));
//                        }
//                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(stmtt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HuespedesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }
    public List<Huespedes> listarHuespedes() {
        List<Huespedes> huespedes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmtt = null;
        ResultSet rs = null;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(SELECT);
            rs = stmtt.executeQuery();
            while (rs.next()) {
                Huespedes huespedesRs = new Huespedes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                huespedes.add(huespedesRs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmtt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HuespedesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return huespedes;
    }

    public List<Huespedes> buscarId(String id) {
        List<Huespedes> huespedes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmtt = null;
        ResultSet rs = null;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(BUSCAR_ID);
            rs = stmtt.executeQuery();
            while (rs.next()) {
                Huespedes huespedesRs = new Huespedes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7));
                huespedes.add(huespedesRs);
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmtt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HuespedesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return huespedes;
    }

    public int Actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setDate(3, fechaN);
            stmt.setString(4, nacionalidad);
            stmt.setString(5, telefono);
            stmt.setInt(6, idReserva);
            stmt.setInt(7, id);
            registros = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HuespedesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }
    
    public int Eliminar(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        var registros = 0;    
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);
            registros = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(stmt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(HuespedesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

}
