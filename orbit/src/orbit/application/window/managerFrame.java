package orbit.application.window;

/**
 * Advance Mangement Interface
 * @author sjorge
 */
/**
 * Imports
 */
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

// vijava
import java.net.*;
import com.vmware.vim25.*;
import com.vmware.vim25.mo.*;

public class managerFrame extends JFrame {

    // variables
    private ServiceInstance si;
    private managerFrame window;
    private Container content;
    private Properties config;

    /**
     * managerFrame Constructor
     * @param si ServiceInstance
     */
    public managerFrame(ServiceInstance serviceInstant, Properties cfg) {
        // self reference
        window = this;
	config = cfg;
        si = serviceInstant;

        // get content
        content = window.getContentPane();

        // main windows setup
        window.setTitle(String.format("Orbit Manager (%s)", config.getProperty("recent.server", "unknown")));
	window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	window.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent w) {
		if (config.getProperty("close.action", "close").equals("login")) {
		    // create login window
		    JFrame loginWindow = new loginFrame();
		    loginWindow.setVisible(true);

                    // disconnect
                    si.getServerConnection().logout();

		    // dispose this window
		    window.dispose();
		} else {
		    System.exit(0);
		}
	    }
	});
        window.setResizable(true);
        window.setSize(new Dimension(750, 500));
        window.setPreferredSize(new Dimension(750, 500));
        //TODO: maximize on open (also check into mac/linux)
        window.centerScreen();
        // icon
        try {
            window.setIconImage(ImageIO.read(window.getClass().getResource("/orbit/application/resources/icons/orbit-icon-16.png")));
        } catch (Exception e) {
            // nothing to do
        }

        // gui
        window.createGUI();
        window.attachEvents();

    }

    /**
     * Center on screen
     */
    public void centerScreen() {
        Dimension dim = getToolkit().getScreenSize();
        Rectangle abounds = getBounds();
        window.setLocation(
                (dim.width - abounds.width) / 2,
                (dim.height - abounds.height) / 2);
        window.requestFocusInWindow();
    }

    /**
     * Attach events to components
     */
    public void attachEvents() {
    }

    /**
     * Create GUI
     */
    public void createGUI() {
        //TODO: advance management gui
    }
}
