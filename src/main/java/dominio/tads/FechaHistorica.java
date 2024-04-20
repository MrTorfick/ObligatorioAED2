package dominio.tads;

import java.util.Date;
import java.util.Objects;

public class FechaHistorica {

    private Date fecha;
    private String descripcion;

    public FechaHistorica(Date fecha) {
        Objects.requireNonNull(fecha);
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FechaHistorica that = (FechaHistorica) o;
            return Objects.equals(fecha, that.fecha);
        }

     */
    @Override
    public boolean equals(Object o) {
        return Objects.equals(fecha, ((FechaHistorica) o).fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha);
    }



    /*
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof FechaHistorica) {
            return ((FechaHistorica) o).fecha.equals(this.fecha);
        }
        return false;
    }
    */
}
