package controller;

import domain.Huespedes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.Conexion;
import jdbc.HuespedesDAO;

/**
 *
 * @author Julian
 */
public class HuespedesController {
    private HuespedesDAO huespedes;
    
    public HuespedesController(){
        try {
            Connection conn = Conexion.getConnection();
            this.huespedes = new HuespedesDAO(conn);
        } catch (SQLException ex) {
            Logger.getLogger(HuespedesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardar(Huespedes huespedes) {
        this.huespedes.guardar(huespedes);
    }
    public List<Huespedes> listarHuespedes() {
        return this.huespedes.listarHuespedes();
    }

    public List<Huespedes> listarHuespedesId(String id) {
        return this.huespedes.buscarId(id);
    }

    public void actualizar(String nombre, String apellido, Date fechaN, String nacionalidad, String telefono, Integer idReserva, Integer id) {
        this.huespedes.Actualizar(nombre, apellido, fechaN, nacionalidad, telefono, idReserva, id);
    }

    public void Eliminar(Integer id) {
        this.huespedes.Eliminar(id);
    }
    
}
