package co.edu.udea.compumovil.studentapp.webservices;

/**
 * Created by jaiber on 20/03/17.
 */

public class ConfigURL {

    public static final String HOST_IP = "http://192.168.1.59"; //Cuando se trabaja con Servidor
    public static final String HOST_CODE = "http://studentapp-mraulio10785903.codeanyapp.com"; //Con CodeAnyWhere

    /*
        Para ambos casos CodeAnyWhere o Local el complemento es el mismo
    */
    public static final String URL_STUDENTS_COMPLEMENT = ":3000/api/Students";
    public static final String URL_CONTAINER_DOWN_COMPLEMENT = ":3000/api/Containers/all/download/";
    public static final String URL_CONTAINER_UP_COMPLEMENT = ":3000/api/Containers/all/upload";

    //Si se trabaja local se quita comentario de estas lineas y se comenta las de CodeAnyWhere
    public static final String URL_STUDENTS = HOST_IP.concat(URL_STUDENTS_COMPLEMENT);
    public static final String URL_CONTAINER_DOWN = HOST_IP.concat(URL_CONTAINER_DOWN_COMPLEMENT);
    public static final String URL_CONTAINER_UP = HOST_IP.concat(URL_CONTAINER_UP_COMPLEMENT);

}
