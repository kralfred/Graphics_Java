package object;

import java.util.ArrayList;

public class ConnectedLine {
    private Line a;
    private ArrayList<Line> connectedLines;
    private ArrayList<Point> connections;

    public ConnectedLine(Line a, ArrayList<Line> connectedLines) {
        this.a = a;
        this.connectedLines = connectedLines;
    }

}
