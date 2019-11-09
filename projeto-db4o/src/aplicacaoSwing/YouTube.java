package aplicacaoSwing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class YouTube {
	
	public static void main(String[] args) {
	    NativeInterface.open();
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            JFrame frame = new JFrame("YouTube Viewer");
	            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	            frame.getContentPane().add(getBrowserPanel(), BorderLayout.CENTER);
	            frame.setSize(800, 600);
	            frame.setLocationByPlatform(true);
	            frame.setVisible(true);
//	            frame.setExtendedState(frame.MAXIMIZED_BOTH);
	        }
	    });
	    NativeInterface.runEventPump();
	    // don't forget to properly close native components
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            NativeInterface.close();
	        }
	    }));
	}

	public static JPanel getBrowserPanel() {
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    JWebBrowser webBrowser = new JWebBrowser();
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    webBrowser.navigate("https://www.youtube.com/embed?listType=search&list="+JOptionPane.showInputDialog("digite o nome do Genero")+"-trailer-legendado");
	    return webBrowserPanel;
	}

}
