import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manager
{
    private int rows,columns;
    private int[] initialState;
    private long elapsedTime;
    private boolean isSolutionFound;

    Manager(String strategy, String how, String initialPath,String solutionPath, String statsPath)
    {
        long start = System.nanoTime();

        //do ogarnięcie!!
        loadInitialValues(initialPath);

        if(strategy.equals("BFS"))
        {
            BFS algorithm = new BFS(how.toCharArray(),initialState);
            isSolutionFound=algorithm.findSolution();
            elapsedTime = System.nanoTime() - start;
        }
    }

    /*wczytac plik
    * w k
    * 1 2 3
    * 4 5 6
    * */
    void loadInitialValues(String path)
    {
        try (Stream<String> stream = Files.lines(Paths.get(path)))
        {
            String s =  stream.collect(Collectors.joining(" "));
            stream.close();

            int[] arr = Arrays.stream(s.split(" "))
                    .filter(word -> !word.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            rows=arr[0];
            columns=arr[1];
            initialState= Arrays.copyOfRange(arr, arr[3], arr.length);


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
