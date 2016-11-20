package com.barcode.server;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.mortbay.jetty.Server;
import org.mortbay.xml.XmlConfiguration;

public class Startup {
    
    private static Log log;
    
	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();
		LogManager.getRootLogger().setLevel(Level.INFO);
		log = LogFactory.getLog(Startup.class);
		if (args.length == 0) {
			args = new String[] { "etc/server.xml" };
		}
		String home = System.getProperty("jiudl.home");
		if (home == null || "".equals(home)) {
			home = ".";
		}
		for (int i = 0; i < args.length; i++) {
			PropertyConfigurator.configure(home
					+ "/etc/log4j.properties");
			InputStream in = null;
            String fileName = args.length > 0 ? args[0] : "etc/server.xml";
			try {
				File configFile = new File(fileName);
				in = new FileInputStream(configFile);
				Server server = new Server();
				XmlConfiguration configuration = new XmlConfiguration(in);
				configuration.configure(server);
				in.close();
				server.start();
			} catch (Exception e) {
				log.error("Failed to load server startup config: " + fileName,
						e);
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
	}

}
