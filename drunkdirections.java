import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class drunkdirections {

    public static void main(String[] args) throws IOException {
        String path = "/Users/hectorjose/Downloads/puzzleKey2.txt";
        String directions = new String(Files.readAllBytes(Paths.get(path)));

        System.out.println("The total amount of houses that receive at least one package is: " + houses(directions));
        System.out.println("The next year, the total amount of houses that receive at least one package is: " + robohouses(directions));
    }

    private static int houses(String directions) {
        Set<String> housesvisited = new HashSet<>();
        int x = 0, y = 0;
        housesvisited.add(x + "," + y);

        for (char direction : directions.toCharArray()) {
            switch (direction) {
                case '^': y++; break;

                case 'v': y--; break;

                case '>': x++; break;

                case '<': x--; break; 
            }
            housesvisited.add(x + "," + y);
        }

        return housesvisited.size();
    }

    private static int robohouses(String directions) {
        Set<String> housesvisitedbyboth = new HashSet<>();
        int X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;
        housesvisitedbyboth.add(X1 + "," + Y1);

        boolean fabrizzioturn = true;
        for (char direction : directions.toCharArray()) {
            if (fabrizzioturn) {
                switch (direction) {
                    case '^': Y1++; break;

                    case 'v': Y1--; break;

                    case '>': X1++; break;

                    case '<': X1--; break;
                }
                housesvisitedbyboth.add(X1 + "," + Y1);
            } else {
                switch (direction) {
                    case '^': Y2++; break;

                    case 'v': Y2--; break;

                    case '>': X2++; break;

                    case '<': X2--; break;
                }
                housesvisitedbyboth.add(X2 + "," + Y2);
            }
            fabrizzioturn = !fabrizzioturn;
        }

        return housesvisitedbyboth.size();
    }
}
