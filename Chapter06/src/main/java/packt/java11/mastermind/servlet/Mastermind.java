// START SNIPPET MastermindServlet
package packt.java11.mastermind.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Mastermind extends HttpServlet {
    private static final Logger log =
            LoggerFactory.getLogger(Mastermind.class);

    // START SNIPPET Mastermind_doGet
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        var sessionSaver = new GameSessionSaver();
        sessionSaver.reset(request.getSession());
        doPost(request, response);
    }
   // END SNIPPET

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        Injector injector =
                Guice.createInjector(new MastermindModule());
        MastermindHandler handler =
                injector.getInstance(MastermindHandler.class);
        handler.handle(request, response);
    }
}
//END SNIPPET