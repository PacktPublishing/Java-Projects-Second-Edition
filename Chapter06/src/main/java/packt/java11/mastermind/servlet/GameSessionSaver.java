package packt.java11.mastermind.servlet;

import packt.java11.mastermind.ColorManager;
import packt.java11.mastermind.Table;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// START SNIPPET GameSessionSaver
public class GameSessionSaver {
    private static final String STATE_NAME = "GAME_STATE";
    @Inject
    private HtmlTools html;
    @Inject
    Table table;
    @Inject
    ColorManager manager;

    public void save(HttpSession session) {
        var params = convertTableToMap();
        session.setAttribute(STATE_NAME, params);
    }

    public void reset(HttpSession session) {
        session.removeAttribute(STATE_NAME);
    }

    public Map<String, String> restore(HttpSession session) {
        return (Map<String, String>)
                Optional.ofNullable(session.getAttribute(STATE_NAME))
                        .orElse(new HashMap<>());
    }

    private Map<String, String> convertTableToMap() {
        var params = new HashMap<String, String>();
        for (int row = 0; row < table.nrOfRows(); row++) {
            for (int column = 0;
                 column < table.nrOfColumns();
                 column++) {
                params.put(html.paramNameGuess(row, column),
                        table.getColor(row, column).toString());
            }
            params.put(html.paramNameFull(row),
                    "" + table.getFull(row));
            params.put(html.paramNamePartial(row),
                    "" + table.getPartial(row));
        }
        return params;
    }
}
// END SNIPPET