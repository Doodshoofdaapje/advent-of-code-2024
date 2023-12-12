import java.util.List;
import java.util.Map;

public class Game {
    public int id;
    public List<Map<String, Integer>> subsets;

    public Game(int id, List<Map<String, Integer>> subsets) {
        this.id = id;
        this.subsets = subsets;
    }

    public void printGame() {
        String outputString = String.format("Game id = %d, subsets = ", id);
        System.out.println(outputString);
        for (Map<String, Integer> map : subsets) {
            System.out.println(map);
        }
    }
}
