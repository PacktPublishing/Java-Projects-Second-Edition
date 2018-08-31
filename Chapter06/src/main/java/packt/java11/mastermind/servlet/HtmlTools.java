// START SNIPPET HtmlTools
package packt.java11.mastermind.servlet;

import packt.java11.mastermind.Color;
import packt.java11.mastermind.Table;

import javax.inject.Inject;
import javax.inject.Named;

public class HtmlTools {
    @Inject
    Table table;

    @Inject
    @Named("nrColumns")
    private int NR_COLUMNS;

    public String tag(String tagName, String... attributes) {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append((tagName));
        for (int i = 0; i < attributes.length; i += 2) {
            sb.append(" ").
                    append(attributes[i]).
                    append("=\"").
                    append(attributes[i + 1]).
                    append("\"");
        }
        sb.append(">");
        return sb.toString();
    }

    public String inputBox(String name, String value) {
        return tag("input", "type",
                "text", "name", name, "value", value, "size", "1");
    }

    public String colorToHtml(Color color, int row, int column) {
        return tag("div",
                "class", "color" + color) +
                tag("/div") +
                tag("div",
                        "class", "spacer") +
                tag("/div");
    }

    public String paramNameFull(int row) {
        return "full" + row;
    }

    public String paramNamePartial(int row) {
        return "partial" + row;
    }

    public String paramNameGuess(int row, int column) {
        return "guess" + row + column;
    }

    public String tableToHtml() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<link rel=\"stylesheet\"")
                .append(" type=\"text/css\" href=\"colors.css\">");
        sb.append("<title>Mastermind guessing</title>");
        sb.append("<body>");
        sb.append(tag("form",
                "method", "POST",
                "action", "master"));

        for (int row = 0; row < table.nrOfRows(); row++) {
            for (int column = 0; column < NR_COLUMNS; column++) {
                final String html =
                        colorToHtml(table.getColor(row, column),
                                row, column);
                sb.append(html);
            }
            if (row < table.nrOfRows() - 1) {
                sb.append("" + table.getFull(row));
                sb.append(tag("div", "class", "spacer"))
                        .append(tag("/div"));
                sb.append("" + table.getPartial(row));
            } else {
                sb.append(inputBox(paramNameFull(row), "" + table.getFull(row)));
                sb.append(inputBox(paramNamePartial(row), "" + table.getPartial(row)));
            }
            sb.append("<p>");
        }
        return sb.toString();
    }
}
//END SNIPPET