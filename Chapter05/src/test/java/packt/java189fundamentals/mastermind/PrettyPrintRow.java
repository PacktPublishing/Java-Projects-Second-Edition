package packt.java189fundamentals.mastermind;

// START SNIPPET PrettyPrintRow_ch05
public class PrettyPrintRow {

    public static String pprint(Row row) {
        var string = "";
        var pRow = new PrintableRow(row);
        for (int i = 0; i < pRow.nrOfColumns(); i++) {
            string += pRow.pos(i);
        }
        string += " ";
        string += pRow.full();
        string += "/";
        string += pRow.partial();
        return string;
    }
}
//END SNIPPET
