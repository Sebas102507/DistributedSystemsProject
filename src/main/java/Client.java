import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        System.out.println("Loading...");
        ServerFunctionsInterface serverFunctionsInterface;
        String serverAddress= Client.getServerIPaddress();
        int serverPort=Client.getServerPort();
        try {
            Registry registry= LocateRegistry.getRegistry(serverAddress,serverPort);
            serverFunctionsInterface = (ServerFunctionsInterface) (registry.lookup("server__"));
            System.out.println("Ready...!!");
            Client.chooseOption(serverFunctionsInterface);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static String getServerIPaddress(){
        String ip= JOptionPane.showInputDialog("Enter the server IP Address: ");
        return ip;
    }

    static int getServerPort(){
        int port= Integer.parseInt(JOptionPane.showInputDialog("Enter the server port: "));
        return port;
    }

    static void chooseOption(ServerFunctionsInterface serverFunctionsInterface) throws Exception{
        String id;
        String group;
        String name;
        int option;
        try{
            while(true){
                option= Integer.parseInt(JOptionPane.showInputDialog("Elige una opción: \n1) Ver nombre de estudiante \n2) Ver promedio de estudiante\n3) Ver grupo de estudiante  \n4) Ver estudiantes de un grupo \n5) Salir"));
                if(option==1){
                    id= Client.getStudentId();
                    JOptionPane.showMessageDialog(null,"El nombre del estudiante es: "+serverFunctionsInterface.getStudentName(id));
                }else if(option==2){
                    int opt= Client.getOption();
                    if (opt==1){
                        name= Client.getStudentName();
                        JOptionPane.showMessageDialog(null,"El promedio del estudiante es : "+serverFunctionsInterface.getStudentGradesAverage(name,null));
                    }else{
                        id= Client.getStudentId();
                        JOptionPane.showMessageDialog(null,"El promedio del estudiante es : "+serverFunctionsInterface.getStudentGradesAverage(null,id));
                    }
                }else if(option==3){
                    id= Client.getStudentId();
                    JOptionPane.showMessageDialog(null,"El grupo del estudiante es : "+serverFunctionsInterface.getStudentGroup(id));
                }else if(option==4){
                    group= Client.getGroup();
                    String studentsName= Client.getStudentsName(serverFunctionsInterface.getStudentsFromGroup(group));
                    JOptionPane.showMessageDialog(null,studentsName);
                }else if(option==5){
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Finished process...!!");
            e.printStackTrace();
        }
    }
    static String getStudentId() throws  Exception{
        String id= JOptionPane.showInputDialog("Ingresa el ID del estudiante: ");
        return id;
    }

    static String getStudentName() throws  Exception{
        String id= JOptionPane.showInputDialog("Ingresa el nombre del estudiante: ");
        return id;
    }

    static String getGroup() throws  Exception{
        String group= JOptionPane.showInputDialog("Ingresa el nombre del grupo: ");
        return group;
    }

    static String getStudentsName(List<Student> stds) throws  Exception{
        String info= "The students of the group is: \n";
        for (Student std:stds) {info=info.concat(std.getNombre()+"\n");}
        return info;
    }

    static int getOption() throws  Exception{
        int option= Integer.parseInt(JOptionPane.showInputDialog("Elige una opción: \n1) Ver usando nombre del estudiante \n2) Ver usando Id del estudiante"));
        return option;
    }



}

