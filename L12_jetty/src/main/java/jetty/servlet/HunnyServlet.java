package jetty.servlet;

import jetty.PageMaker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by carapooh on 15.10.2017.
 */
public class HunnyServlet extends HttpServlet {
    
    public static final String HUNNY_PAGE_TEMPLATE = "hunny.html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println(PageMaker.getInstance().getPage(HUNNY_PAGE_TEMPLATE, null));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
