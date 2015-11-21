package com.eboji.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 项目启动的入口类
 * @author zhoucl
 */
public class Bootstrap {
	private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
	
	private static final Daemon daemon = Daemon.getInstance();
	
	static {
		ShutdownHook.doShutdownHook();
	}

	public static void main(String[] args) {
		
		checkArgs(args);
		
		try {
			String command = "start";
			if(args.length > 0) {
				command = args[args.length - 1];
			}
			
			if(command.equals("start")) {
				daemon.init();
				daemon.start();
			} else if(command.equals("stop")) {
				daemon.stop();
			} else {
				logger.warn("Bootstrap: command \"" + command + "\" does not exist.");
			}
		} catch (Exception e) {
			logger.error("server start fail!", e);
			System.exit(1);
		}
	}
	
	private static void checkArgs(String[] args) {
		try {
			for (int i = 0; i < args.length; ++i) {
				if (args[i].startsWith("--host")) {
					daemon.setHost(args[i].split("=")[1].trim());
				} else if (args[i].startsWith("--port")) {
					daemon.setPort(Integer.valueOf(args[i].split("=")[1]));
				} else if (args[i].equals("--timeout")) {
					daemon.setSocketTimeout(Integer.valueOf(args[i].split("=")[1]));
				} else if (args[i].startsWith("--protocol")) {
					daemon.setProtocol_type(args[i].split("=")[1].trim());
				} else if (args[i].startsWith("--transport")) {
					daemon.setTransport_type(args[i].split("=")[1].trim());
				} else if (args[i].equals("--ssl")) {
					daemon.setSsl(true);
				} else if (args[i].equals("--help")) {
					System.out.println("Allowed options:");
					System.out.println("  --help\t\t\tProduce help message");
					System.out.println("  --host=arg (=" + daemon.getHost() + ")\tHost to connect");
					System.out.println("  --port=arg (=" + daemon.getPort() + ")\t\tPort number to connect");
					System.out.println("  --transport=arg (=" + daemon.getTransport_type() + ")\tTransport: buffered, framed, fastframed, http");
					System.out.println("  --protocol=arg (=" + daemon.getProtocol_type() + ")\tProtocol: binary, json, compact");
					System.out.println("  --ssl\t\t\t\tEncrypted Transport using SSL");
					System.out.println("  --server-type=arg (=" + daemon.getServer_type() +")\n\t\t\t\tType of server: simple, nonblocking, hsha, threaded-selector, thread-pool");
					System.exit(0);
				}
			}
		} catch (Exception x) {
			logger.error("Can not parse arguments! See --help");
			System.exit(1);
		}
	}
}
