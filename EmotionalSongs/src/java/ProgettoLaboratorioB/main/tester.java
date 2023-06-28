package ProgettoLaboratorioB.main;

public class tester {

    public static void main(String[] args) {
        String os = System.getProperty("os.name");
        if(os.equals("Mac OS X"))
            System.out.println("Mac OS X");
        else if(os.equals("Windows"))
            System.out.println("Windows");
        else if(os.equals("Linux"))
            System.out.println("Linux");
        else
            System.out.println("OS not supported");
    }
}
