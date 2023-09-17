public class Incidencia {

    static int contador;
    static int pendientes;
    static {
        contador = 1;
        pendientes = 0;
    }
    Integer codigo;
    boolean estaPendiente;
    Integer numPuesto;
    String descripcion;
    String solucion;

    public Incidencia(int numPuesto, String descripcion) {
        this.codigo = contador++;
        this.numPuesto = numPuesto;
        this.estaPendiente = true;
        this.descripcion = descripcion;
        this.solucion = "";
        pendientes++;
    }

    public boolean isEstaPendiente() {
        return estaPendiente;
    }

    public void setEstaPendiente(boolean estaPendiente) {
        this.estaPendiente = estaPendiente;
    }

    public Integer getNumPuesto() {
        return numPuesto;
    }

    public void setNumPuesto(Integer numPuesto) {
        this.numPuesto = numPuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public static Integer getPendientes() {
        return pendientes;
    }

    public boolean resuelve(String solucion) {
        if (estaPendiente) {
            this.estaPendiente = false;
            this.solucion = solucion;
            pendientes--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Incidencia %d - Puesto: %d - %s", codigo, numPuesto, descripcion)
                .concat(this.estaPendiente ? "Pendiente " : ("Resuelta - " + solucion));
    }
}
