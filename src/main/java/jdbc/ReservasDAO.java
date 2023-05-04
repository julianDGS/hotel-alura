package jdbc;

import domain.Reserva;
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
public class ReservasDAO {
    
    private static final String INSERT = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, formaPago) VALUES (?, ?, ?, ?)";
    private static final String SELECT = "SELECT id, fecha_entrada, fecha_salida, valor, forma_pago FROM reservas";
    private static final String BUSCAR_ID = "SELECT id, fecha_entrada, fecha_salida, valor, formaPago FROM reservas WHERE id = ?";
    private static final String UPDATE = "UPDATE reservas SET fecha_entrada = ?, fecha_salida = ?, valor = ?, formaPago = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM reservas WHERE id = ?";
    
    private Connection conn;
	
    public ReservasDAO() {

    }

    public ReservasDAO(Connection conn) {
        this.conn = conn;
    }
	
    public int guardar(Reserva reserva) {
        Connection conn = null;
        PreparedStatement stmtt = null;
        int registros = 0;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(INSERT);
            stmtt.setDate(1, reserva.getfechaE());
            stmtt.setDate(2, reserva.getfechaS());
            stmtt.setString(3, reserva.getvalor());
            stmtt.setString(4, reserva.getformaPago());

            registros = stmtt.executeUpdate();
        } catch (SQLException e) {
                throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(stmtt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    public List<Reserva> buscar() {
        List<Reserva> reservas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmtt = null;
        ResultSet rs = null;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(SELECT);
            rs = stmtt.executeQuery();
            while (rs.next()) {
                Reserva produto = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                reservas.add(produto);
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
                Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reservas;
    }

    public List<Reserva> buscarId(String id) {
        List<Reserva> reservas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmtt = null;
        ResultSet rs = null;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(BUSCAR_ID);
            rs = stmtt.executeQuery();
            while (rs.next()) {
                Reserva produto = new Reserva(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5));
                reservas.add(produto);
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
                Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return reservas;
    }

    public int Eliminar(Integer id) {
        Connection conn = null;
        PreparedStatement stmtt = null;
        var registros = 0;    
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmtt = conn.prepareStatement(DELETE);
            stmtt.setInt(1, id);
            registros = stmtt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Conexion.close(stmtt);
                if (this.conn == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }

    public int Actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conn != null ? this.conn : Conexion.getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setDate(1, fechaE);
            stmt.setDate(2, fechaS);
            stmt.setString(3, valor);
            stmt.setString(4, formaPago);
            stmt.setInt(5, id);
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
                Logger.getLogger(ReservasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }
    
}
