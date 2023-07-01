package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;

public class User implements Serializable, Comparable<User>
{

    private final static long serialVersionUID = 1L;
    String username; // primary key
    String password;
    String nome;
    String cognome;
    String indirizzo;
    String CF;
    String email;

    /**
     * Only for test.
     */
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
    /// This following GETTER functions are used to get the values of the attributes of the class,
    /// every time system needs to know the values of the attributes of the class; use this.
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
    /// when the user subscribe for the first time in the application;
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
