package jetty;

import cache.CacheEngineSoftReferenceImpl;
import jetty.servlet.AdminServlet;
import jetty.servlet.CacheEngineServlet;
import jetty.servlet.HunnyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by carapooh on 16.10.2017.
 */
public class CacheEngineServer extends CacheEngineSoftReferenceImpl {

    private final static int PORT = 2323;
    private final static String PUBLIC_HTML = "public_html";
    private Server server = new Server(PORT);
    private ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

    public final static String ADMIN_PATHSPEC = "/admin";
    public final static String CACHE_PATHSPEC = "/cache";
    public final static String HUNNY_PATHSPEC = "/hunny";
    public final static String CACHE_SERVER_VAR = "cache";

    public CacheEngineServer(int maxSize, long lifeTimeInMilliseconds,
                             long idleTimeInMilliseconds) throws Exception {
        super(maxSize, lifeTimeInMilliseconds, idleTimeInMilliseconds);
    }

    public void startServer() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);
        context.addServlet(AdminServlet.class, ADMIN_PATHSPEC);
        context.addServlet(HunnyServlet.class, HUNNY_PATHSPEC);
        context.addServlet(CacheEngineServlet.class, CACHE_PATHSPEC);
        context.setAttribute(CACHE_SERVER_VAR, this);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
    }

    public void joinServer() throws InterruptedException {
        server.join();
    }
}
