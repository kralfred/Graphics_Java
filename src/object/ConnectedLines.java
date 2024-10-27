package object;

import java.util.ArrayList;
import java.util.HashMap;

public class ConnectedLines {

    // Use HashMap for fast lookup
    private HashMap<Line, Boolean> linesMap;
    private HashMap<Line, ArrayList<Point>> connectionPointsMap;

    public ConnectedLines() {
        linesMap = new HashMap<>();
        connectionPointsMap = new HashMap<>();
    }

    // Add a line to the map
    public void addLine(Line line) {
        linesMap.put(line, true); // or any relevant value
    }

    public void addConnectionPoint(Line line, Point connectionPoint) {
        // Ensure the line exists in both maps
        if (linesMap.containsKey(line)) {
            connectionPointsMap.get(line).add(connectionPoint);
        } else {
            addLine(line); // Add the line if it's not present
            connectionPointsMap.get(line).add(connectionPoint);
        }
    }

    // Check if a line is in the map
    public boolean containsLine(Line line) {
        return linesMap.containsKey(line);
    }

    // Optional: Get all lines if needed
    public HashMap<Line, Boolean> getLines() {
        return linesMap;
    }

    // Optional: Method to get the number of crossed lines
    public int getLineCount() {
        return linesMap.size();
    }
}

