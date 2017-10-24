package jetty.servlet;

import cache.SoftReferenceCacheElement;
import jetty.CacheEngineServer;
import jetty.PageMaker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by carapooh on 16.10.2017.
 */
public class CacheEngineServlet extends HttpServlet {

    public static final String CACHE_PAGE_TEMPLATE = "cache.html";
    private static final String REFRESH_VARIABLE_NAME = "refreshPeriod";
    private static final int PERIOD_MS = 10000;
    private static String ADMIN_LOGIN = "carapooh";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login =  (String) request.getSession().getAttribute("login");
        String loginPage = (String) request.getAttribute("login");
        if (!((String) (request.getSession().getAttribute("login"))).equals(ADMIN_LOGIN)){
            response.getWriter().println(PageMaker.getInstance().getPage(HunnyServlet.HUNNY_PAGE_TEMPLATE, null));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put(REFRESH_VARIABLE_NAME, String.valueOf(PERIOD_MS));
            CacheEngineServer cacheEngineServer = (CacheEngineServer) getServletContext().getAttribute(CacheEngineServer.CACHE_SERVER_VAR);
            pageVariables.put("size", cacheEngineServer.getSize());
            pageVariables.put("hits", cacheEngineServer.getHitCount());
            pageVariables.put("misses", cacheEngineServer.getMissCount());

            response.getWriter().println(PageMaker.getInstance().getPage(CACHE_PAGE_TEMPLATE, pageVariables));
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

}
