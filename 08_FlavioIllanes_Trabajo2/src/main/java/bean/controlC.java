
package bean;

import dao.usuarioD;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.usuario;
import services.javaMail;

@Named(value = "controlC")
@SessionScoped
public class controlC implements Serializable{
    
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private usuario usu;
    private usuarioD dao;
    
    public controlC(){
        usu = new usuario();
        dao = new usuarioD();
    }
    
    
    
    
    public void validar(){
        try {
            int caso = 0;
            caso = dao.validar(usu,caso);
            
            switch(caso){
                case 0:
                    String contra = "contra123";
                    contraseña = contra;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Contraseña enviada"));
                    javaMail.enviarConGMail(usu.getCorreo(),"Saludos" , "Te doy la bienvenida y tu contraseña es esto: " + contra);
                    break;
                case 1:
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "El nombre y apellido ya existe"));
                    break;
                
                
            }
            
        } catch (Exception e) {
            System.out.println("Error en validarC" + e.getMessage());
        }
    }
    
    public void validarContrasena(){
        if(contraseña.equals(usu.getContrasena())){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Registro Exitoso"));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "La contraseña no es igual"));
        }
    }

    public usuario getUsu() {
        return usu;
    }

    public void setUsu(usuario usu) {
        this.usu = usu;
    }

    public usuarioD getDao() {
        return dao;
    }

    public void setDao(usuarioD dao) {
        this.dao = dao;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    
    
}
