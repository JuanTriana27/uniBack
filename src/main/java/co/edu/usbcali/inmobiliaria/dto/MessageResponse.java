package co.edu.usbcali.inmobiliaria.dto;

public class MessageResponse {
    private String mensaje;

    public MessageResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
