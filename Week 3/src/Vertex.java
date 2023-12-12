import java.util.ArrayList;
import java.util.List;

public class Vertex {
    public Integer x;
    public Integer y;
    public Integer width;
    public String value;

    public Vertex(Integer x, Integer y, Integer width, String value) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.value = value;
    }

    public boolean isAdjacentTo(Vertex vertex) {
        if (isInRange(vertex.x, this.x - 1, this.x + this.width) || isInRange(vertex.x + vertex.width, this.x, this.x + this.width + 1)) {
            if (isInRange(vertex.y, this.y - 1, this.y + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInRange(Integer x, Integer lowerBound, Integer upperBound) {
        return (x >= lowerBound && x <= upperBound);
    }
}
