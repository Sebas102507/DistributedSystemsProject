import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {
    private String grupo;
    private String Id;
    private String nombre;
    private List<Double> notasTalleres;


    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Double> getNotasTalleres() {
        return notasTalleres;
    }

    public void setNotasTalleres(List<Double> notasTalleres) {
        this.notasTalleres = notasTalleres;
    }
}
