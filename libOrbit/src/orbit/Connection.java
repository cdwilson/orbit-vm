package orbit;

/**
 * Imports
 */
import java.net.URL;
import orbit.exceptions.*;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

/**
 *
 * @author sjorge
 */
public class Connection {
    /** configuration */
    private String ServiceURL;
    private String Username = "root";
    private String Password;

    /**
     * Create connection and return ServiceInstance
     * @return ServiceInstance for this connection
     */
    public ServiceInstance connect() throws Exception {
        // locals
        ServiceInstance si;


        si = null;
        // validate connection
        if (Username.isEmpty()) {
            throw new ConnectionException("Empty Username.");
        }

        if (Password.isEmpty()) {
            throw new ConnectionException("Empty Password.");
        }

        if (ServiceURL.isEmpty()) {
            throw new ConnectionException("Empty ServiceURL.");
        }

        // validate serviceurl
        if (!ServiceURL.endsWith("/sdk")) {
            ServiceURL += "/sdk";
        }

        // connect
        si = new ServiceInstance(new URL(ServiceURL), Username, Password, true);

        return si;
    }


    /**
     * @return the ServiceURL
     */
    public String getServiceURL() {
        return ServiceURL;
    }

    /**
     * @param ServiceURL the ServiceURL to set
     */
    public void setServiceURL(String ServiceURL) {
        this.ServiceURL = ServiceURL;
    }

    /**
     * @return the Username
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username the Username to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    
}