package packt.java11.mastermind.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java11.mastermind.*;
import packt.java11.mastermind.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

public class MastermindHandler {

    //START SNIPPET MastermindHandler_injections
    @Inject
    @Named("nrColors")
    private int NR_COLORS;
    @Inject
    @Named("nrColumns")
    private int NR_COLUMNS;
    @Inject
    private HtmlTools html;
    @Inject
    Table table;
    @Inject
    ColorManager manager;
    @Inject
    Guesser guesser;
    // END SNIPPET
    @Inject
    GameSessionSaver sessionSaver;
    private static Logger log = LoggerFactory.getLogger(MastermindHandler.class);

    // START SNIPPET MastermindHander_handle
    public void handle(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        Game game = buildGameFromSessionAndRequest(request);
        Guess newGuess = guesser.guess();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (game.isFinished() || newGuess == Guess.none) {
            displayGameOver(out);
        } else {
            log.debug("Adding new guess {} to the game", newGuess);
            game.addGuess(newGuess, 0, 0);
            // SNIPPET SKIP 1 LINE
            sessionSaver.save(request.getSession()); // note the added line
            displayGame(out);
        }
        bodyEnd(out);
    }
    //END SNIPPET

    private void displayGameOver(PrintWriter out) throws IOException {
        out.println(html.tableToHtml());
        out.println("</form>");
        out.println("Game finished, no more guesses");
    }

    private void displayGame(PrintWriter out) throws IOException {
        out.println(html.tableToHtml());
        out.println(html.tag("input", "type", "submit", "value", "submit"));
        out.println("</form>");

    }

    private void bodyEnd(PrintWriter out) {
        out.println("</body></head></html>");
    }

    // START SNIPPET buildGameFromSessionAndRequest
    private Game buildGameFromSessionAndRequest(HttpServletRequest request) {
        var game = buildGameFromMap(sessionSaver.restore(request.getSession()));
        var params = toMap(request);
        int row = getLastRowIndex(params);
        log.debug("last row is {}", row);
        if (row >= 0) {
            var full = Integer.parseInt(params.get(html.paramNameFull(row)));
            var partial = Integer.parseInt(params.get(html.paramNamePartial(row)));
            log.debug("setting full {} and partial {} for row {}", full, partial, row);
            table.setPartial(row, partial);
            table.setFull(row, full);
            if (full == table.nrOfColumns()) {
                game.setFinished();
            }
        }
        return game;
    }
    // END SNIPPET

    private static final int MAX_ROWS = 10;

    private int getLastRowIndex(Map<String, String> params) {
        int row = -1;
        for (int i = 0; i < MAX_ROWS; i++) {
            if (params.containsKey(html.paramNameFull(i))) {
                row = i;
            }
        }
        log.debug("last row is {}", row);
        return row;
    }

    // START SNIPPET buildGameFromRequest
    private Game buildGameFromRequest(HttpServletRequest request) {
        return buildGameFromMap(toMap(request));
    }

    private Map<String, String> toMap(HttpServletRequest request) {
        log.debug("converting request to map");
        return request.getParameterMap().entrySet().
                stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()[0]));
    }
    // END SNIPPET

    // START  SNIPPET buildGameFromMap
    private Game buildGameFromMap(Map<String, String> params) {
        var secret = new Guess(new Color[NR_COLUMNS]);
        var game = new Game(table, secret);
        for (int row = 0;
             params.containsKey(html.paramNameGuess(row, 0));
             row++) {
            Color[] colors = getRowColors(params, row);
            Guess guess = new Guess(colors);
            var full = Integer.parseInt(params.get(html.paramNameFull(row)));
            var partial = Integer.parseInt(params.get(html.paramNamePartial(row)));
            log.debug("Adding guess to game");
            game.addGuess(guess, full, partial);
        }
        return game;
    }
    // END SNIPPET

    private Color[] getRowColors(Map<String, String> params, int row) {
        Color[] colors = new Color[NR_COLUMNS];
        for (int column = 0; column < NR_COLUMNS; column++) {
            String letter = params.get(html.paramNameGuess(row, column));
            colors[column] = colorFrom(letter);
            log.debug("Processing guess{}{} = {}", row, column, colors[column]);
        }
        return colors;
    }

    private Color colorFrom(String letter) {
        Color color = manager.firstColor();
        while (!color.toString().equals(letter)) {
            if (manager.thereIsNextColor(color)) {
                color = manager.nextColor(color);
            } else {
                return null;
            }
        }
        return color;
    }

}
