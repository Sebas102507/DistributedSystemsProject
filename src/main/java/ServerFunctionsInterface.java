import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerFunctionsInterface extends Remote {
    public String getStudentName(String id) throws RemoteException;
    public double getStudentGradesAverage(String name, String id) throws RemoteException;
    public String getStudentGroup(String id) throws RemoteException;
    public List<Student> getStudentsFromGroup(String group) throws RemoteException;
}
