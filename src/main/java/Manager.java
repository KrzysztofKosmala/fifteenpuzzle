import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Manager
{
    private int rows,columns;
    private int[] initialState;
    private char[] solutionMoves;
    private long elapsedTimeNano;
    private boolean isSolutionFound;
    private double elapsedTimeMili;

    Manager(String strategy, String how, String initialPath, String solutionPath, String statsPath)
    {
        long start = System.nanoTime();

        loadInitialValues(initialPath);

        if(strategy.equals("bfs"))
        {

            Strategies algorithm = new BFS(how.toCharArray(),initialState, rows, columns);
            Arrays.toString(initialState);


            isSolutionFound=algorithm.findSolution();
            solutionMoves=algorithm.getFamilyLine();
            elapsedTimeNano = System.nanoTime() - start;
            elapsedTimeMili = (double)elapsedTimeNano / 1000000.0;
            saveSolution(solutionPath,solutionMoves);
            saveStats(statsPath,algorithm.getSolved().getParentCounter(),algorithm.getVisitedStates(),algorithm.getProcessedStates(),algorithm.getMaxDepthOfRecursion(),elapsedTimeMili);

        }

        else if(strategy.equals("dfs"))
        {
            Strategies algorithm = new DFS(how.toCharArray(),initialState, rows, columns);

            isSolutionFound=algorithm.findSolution();
            solutionMoves=algorithm.getFamilyLine();
            elapsedTimeNano = System.nanoTime() - start;
            elapsedTimeMili = (double)elapsedTimeNano / 1000000.0;
            saveSolution(solutionPath,solutionMoves);
            saveStats(statsPath,algorithm.getSolved().getParentCounter(),algorithm.getVisitedStates(),algorithm.getProcessedStates(),algorithm.getMaxDepthOfRecursion(),elapsedTimeMili);
        }

        else if(strategy.equals("astr"))
        {
            Strategies algorithm = new ASTAR(how.toCharArray(),initialState, rows, columns);

            isSolutionFound=algorithm.findSolution();
            solutionMoves=algorithm.getFamilyLine();
            elapsedTimeNano = System.nanoTime() - start;
            elapsedTimeMili = (double)elapsedTimeNano / 1000000.0;
            saveSolution(solutionPath,solutionMoves);
            saveStats(statsPath,algorithm.getSolved().getParentCounter(),algorithm.getVisitedStates(),algorithm.getProcessedStates(),algorithm.getMaxDepthOfRecursion(),elapsedTimeMili);
        }
    }


    private void loadInitialValues(String path)
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

            initialState= Arrays.copyOfRange(arr, 2, arr.length);


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void saveSolution(String path, char[] familyLine)
    {
        File fout = new File(path);
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(fout);


            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            if (isSolutionFound)
            {
                bw.write(Integer.toString(familyLine.length));//ew  na wszystkie ruchy
                bw.newLine();
                bw.write(Arrays.toString(familyLine).replace(", ", "").replace("[", "").replace("]", ""));//ew na wszytskie ruchy
            } else bw.write("-1");

            bw.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    private void saveStats(String path, int parentsLineSize, int allStates, int exploredStates,int maxDepthOfRecursion, double time)
    {
            File fout = new File(path);
            FileOutputStream fos = null;
            try
            {
                fos = new FileOutputStream(fout);


                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                if(isSolutionFound)
                {
                    bw.write(Integer.toString(parentsLineSize));

                }else bw.write("-1");
                bw.newLine();
                bw.write(Integer.toString(allStates));
                bw.newLine();
                bw.write(Integer.toString(exploredStates));
                bw.newLine();
                bw.write(Integer.toString(maxDepthOfRecursion));
                bw.newLine();
                bw.write(String.format( "%.3f", time ));
                bw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }

}


