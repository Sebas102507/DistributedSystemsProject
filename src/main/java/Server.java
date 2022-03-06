import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Server extends UnicastRemoteObject implements ServerFunctionsInterface {

    private  final int PORT= 3030;
    private List<Student> students_db;
    protected Server() throws RemoteException {}


    public static void main(String[] args) throws  Exception{
        System.setProperty("java.rmi.server.hostname","25.74.97.245");
        (new Server()).initServer();
    }

    public void initServer(){
        try {
            this.students_db= getStudentsDB();
            String ip_address= (InetAddress.getLocalHost()).toString();
            System.out.println("Listening in IP: "+ip_address+" PORT: "+PORT);
            Registry registry= LocateRegistry.createRegistry(PORT);
            registry.bind("server__", (ServerFunctionsInterface) this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsDB() throws FileNotFoundException{
        System.out.println("Loading database...");
        //String path="./src/main/java/database.txt";
        String path="./database.txt";
        File db_file= new File(path);
        Scanner scanner= new Scanner(db_file);
        List<Student> students= new ArrayList<Student>();
        while(scanner.hasNextLine()){
            Student new_student= new Student();
            String std_info=scanner.nextLine();
            String[] student=std_info.split(",");
            new_student.setGrupo(student[0]);
            new_student.setId(student[1]);
            new_student.setNombre(student[2]);
            List<Double> grades= new ArrayList<Double>();
            for (int i=3;i<student.length;i++){grades.add(Double.parseDouble(student[i]));}
            new_student.setNotasTalleres(grades);
            students.add(new_student);
        }
        scanner.close();
        return students;
    }


    public String getStudentName(String id) throws RemoteException {
        System.out.println("Finding students name...");
        String name=this.students_db.stream().filter(std->std.getId().equals(id)).findFirst().get().getNombre();
        return name;
    }

    public double getStudentGradesAverage(String name, String id) throws RemoteException {
        System.out.println("Finding students grades average...");
        List<Double> grades;
        if(name!=null){ grades=this.students_db.stream().filter(std->std.getNombre().toLowerCase().equals(name.toLowerCase())).findFirst().get().getNotasTalleres(); }
        else{ grades=this.students_db.stream().filter(std->std.getId().equals(id)).findFirst().get().getNotasTalleres(); }
        double sum= grades.stream().mapToDouble(gr -> gr).sum();
        return sum/grades.size();
    }

    public String getStudentGroup(String id) throws RemoteException {
        System.out.println("Finding students group...");
        String group=this.students_db.stream().filter(std->std.getId().equals(id)).findFirst().get().getGrupo();
        return group;
    }

    public List<Student> getStudentsFromGroup(String group) throws RemoteException {
        System.out.println("Finding students from group...");
        List<Student> students= this.students_db.stream().filter(std->std.getGrupo().equals(group.toUpperCase())).collect(Collectors.toList());
        return students;
    }

}
