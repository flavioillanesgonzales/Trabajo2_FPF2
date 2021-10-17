package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import model.usuario;

public class usuarioD extends Conexion {

    public int validar(usuario usu,int caso) throws Exception {
        String SQL = "SELECT IDUSU FROM USUARIO WHERE NOMPER = ? AND APEPER = ?";
        PreparedStatement ps = this.conectar().prepareCall(SQL);
        ps.setString(1, usu.getNombre());
        ps.setString(2, usu.getApellido());
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            caso = 1;
        }
        return caso;
        
    }

    public void registrar(usuario usu) {

    }

}
