package com.trong.wordcloud.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by tommy on 2017/3/13.
 */
public class JettyServer {

  public static final int PORT = 8080;
  public static final String CONTEXT = "/";
  private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

  public static Server createServerInSource() {
    Server server = new Server();

    // Stop the jetty when shutdown the JVM
    server.setStopAtShutdown(true);

    SelectChannelConnector connector = new SelectChannelConnector();
    connector.setPort(PORT);
    server.addConnector(connector);

    WebAppContext webAppContext = new WebAppContext();
    webAppContext.setContextPath(CONTEXT);
    webAppContext.setResourceBase(DEFAULT_WEBAPP_PATH);
    webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
    webAppContext.setConfigurationDiscovered(true);
    //webAppContext.setWar("/Users//tommy/git/springmvc/springmvc/target/wordcloud.war");
    server.setHandler(webAppContext);
    return server;
  }

  public static void main(String[] args) throws Exception {
    JettyServer jetty = new JettyServer();
    Server server = jetty.createServerInSource();

    server.start();
    server.join();
  }
}
