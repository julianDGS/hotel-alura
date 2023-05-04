package controller;

import domain.Reserva;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.Conexion;
import jdbc.ReservasDAO;

/**
 *
 * @author Julian
 */
public class ReservasController {
    private ReservasDAO reserva;
    
    public ReservasController(){
        try {
            Connection conn = Conexion.getConnection();
            this.reserva = new ReservasDAO(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardar(Reserva reserva) {
        this.reserva.guardar(reserva);
    }

    public List<Reserva> buscar() {
        return this.reserva.buscar();
    }

    public List<Reserva> buscarId(String id) {
        return this.reserva.buscarId(id);
    }

    public void actualizar(Date fechaE, Date fechaS, String valor, String formaPago, Integer id) {
        this.reserva.Actualizar(fechaE, fechaS, valor, formaPago, id);
    }

    public void Eliminar(Integer id) {
        this.reserva.Eliminar(id);
    }
    
}
