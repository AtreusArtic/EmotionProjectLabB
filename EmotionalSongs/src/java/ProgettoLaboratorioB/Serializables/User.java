package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User implements Serializable, Comparable<User>
{
    private final static long serialVersionUID = 1L;
    String username;
    String password;
    String nome;
    String cognome;
    String indirizzo;
    String CF;
    String email;

    public User() {this.username = "test"; this.password = "default"; this.nome = "default" ;this.cognome = "default";this.CF = "deafult";this.indirizzo = "default" ;this.email = "default";}

    public User(String username, String password, String email, String nome, String cognome, String indirizzo, String CF)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.CF = CF;
    }


    /// <summary>
    /// ATTENTION:
    /// This following GETTER functions are used to get the value attributes of the class,
    /// every time the system needs to know the value attributes of the class; use this.
    /// </summary>
    public String GetUsername()
    {
        return this.username;
    }

    public String GetPassword()
    {
        return this.password;
    }

    public String GetEmail()
    {
        return this.email;
    }

    public String GetName() {return this.nome;}

    public String GetSurname() {return this.cognome;}

    public String GetIndirizzo() {return this.indirizzo;}

    public String GetCF() {return this.CF;}


    /// <summary>
    /// ATTENTION:
    /// This following SETTER functions are used to set the values of the attributes of the class,
    /// when the user subscribes for the first time in the application;
    /// </summary>
    public void SetUsername(String username)
    {
        this.username = username;
    }

    public void SetPassword(String password)
    {
        this.password = password;
    }

    public void SetEmail(String email)
    {
        this.email = email;
    }

    public void SetName(String name) {this.nome = name;}

    public void SetSurname(String surname){this.cognome = surname;}

    public void SetIndirizzo(String ind) {this.indirizzo = ind;}

    public void SetCF(String CF) {this.CF = CF;}


    /**
     * This function is used to check if the user's codice fiscale respect the format;
     * @param codiceFiscale the user's codice fiscale;
     * @return true if the codice fiscale respect the format, false otherwise;
     */
    public static boolean CodFiscalePattern(String codiceFiscale){

        Pattern pattern = Pattern.compile("^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$");

        Matcher matcher = pattern.matcher(codiceFiscale);
        return matcher.matches();
    }

    /**
     * This function is used to check if the user's email respects the format;
     * @param email the user's email;
     * @return true if the email respects the format, false otherwise;
     */
    public static boolean EmailPattern(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public String toString() {
        return "User:" +
                "username='" + GetUsername() + '\'' +
                ", password='" + GetPassword() + '\'' +
                ", email='" + GetEmail() + '\'' +
                ", nome='" + GetName() + '\'' +
                ", cognome='" + GetSurname() + '\'' +
                ", indirizzo='" + GetIndirizzo() + '\'' +
                ", CF='" + GetCF() + '\'' +
                '.';
    }
    @Override
    public int compareTo(User user) {return 0;}
}
