package ProgettoLaboratorioB.Serializables;

import java.io.Serializable;

public class User implements Serializable
{

    private final static long serialVersionUID = 1L;
    String username;
    String password;
    String email;

    /**
     * Only for test.
     */
    public User() {this.username = "test"; this.password = "default"; this.email = "default";}

    public User(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
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




}
