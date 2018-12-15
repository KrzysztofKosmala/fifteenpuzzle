import java.util.*;
import java.util.stream.IntStream;


public class ASTAR extends Strategies
{
    private PriorityQueue<Node> frontier = new PriorityQueue<>(10, Comparator.comparingInt(Node::getScore));
    private HashMap<String, Node> explored = new HashMap<>();

    private int[] howInt; // ustawione na sztywno bo kolejność nie ma znaczenia,
    // i tak algorytm wybierze najbardziej opłacalne przejście

    private char[] howCharOperator;

    private Node firstNode;


    ASTAR(char[] how, int[] state, int rows, int columns)
    {
        super(how, rows, columns);

        Node first = makeFirstNode(state);
        first.setParent(first);
        frontier.add(first);
        explored.put(Arrays.toString(first.getStateInNode()), first);
        howInt = new int[]{1, 4, -4, -1};
        howCharOperator = new char[]{'R', 'D', 'U', 'L'};
        firstNode = first;
    }


    @Override
    public boolean findSolution()
    {

        while (!explored.containsKey(Arrays.toString(template)))
        {
            Node node = frontier.peek();
            int j;
            for (j = 0; j < 4; j++)
            {
                if (node.checkRange(howInt[j]))
                {


                    if ((node.getNullLocation() + howInt[j]) != node.getParentNullLocation())
                    {

                        Node newNode = new Node();

                        newNode.setParent(node);
                        newNode.setParentState(node.getStateInNode());
                        newNode.setParentNullLocation();
                        newNode.setStateInNode();
                        newNode.setNullLocation2();
                        newNode.setParentNullLocation();
                        newNode.setOperator(howCharOperator[j]);
                        newNode.move(howInt[j]);

                        if (!ifExistOnExplored(newNode.getStateInNode())  )
                        {
                            if (Arrays.equals("hamm".toCharArray(), howChar))
                            {
                                newNode.setCost(getHammingDistance(newNode.getStateInNode()));
                            }
                            else if (Arrays.equals("manh".toCharArray(), howChar))
                            {
                                newNode.setCost(getManhattanDistance(newNode.getStateInNode()));
                            }
                            int depthOfRecursion=getParents(newNode);
                            if(maxDepthOfRecursion<depthOfRecursion)
                                maxDepthOfRecursion=depthOfRecursion;

                            newNode.setScore(depthOfRecursion + newNode.getCost());
                            visitedStates++;

                            frontier.add(newNode);

                            if (Arrays.equals(newNode.getStateInNode(), template))
                            {
                                newNode.setParentCounter(depthOfRecursion);
                                solved = newNode;
                                return true;
                            }

                        }

                    }

                }
            }
            explored.put(Arrays.toString(node.getStateInNode()),node);
            processedStates++;
            frontier.remove(node);



        }
        return false;
    }

    @Override
    protected int getVisitedStates()
    {
        return visitedStates;
    }

    @Override
    protected int getProcessedStates()
    {
        return processedStates;
    }

    @Override
    int getParents(Node node)
    {
        parentsLine.clear();
        parentsLine.add(node);
        setFamilyLine();
        return parentsLine.size() - 1;
    }

    @Override
    void setFamilyLine()
    {

        while (parentsLine.get(parentsLine.size() - 1).getCost() != -1)
        {
            setFamilyLineLoop(parentsLine.get(parentsLine.size() - 1));
        }

    }
    private boolean ifExistOnExplored(int[] i)
    {
        return explored.containsKey(Arrays.toString(i));
    }
    @Override
    protected boolean ifExistsOnFrontier(int[] i)
    {
        for (Node a : frontier)
        {
            if (Arrays.equals(a.getStateInNode(), i)) return true;

        }
        return false;
    }


    private int getHammingDistance(int[] tab)
    {
        int a = 0;

        int nullLocation = findZero(tab);

        for (int i = 0; i < tab.length; i++)
        {
            if (tab[i] != template[i] && i != nullLocation)
            {
                a++;
            }
        }

        return a;
    }

    private int getManhattanDistance(int[] tab)
    {
        int zero = findZero(tab);
        int sum = 0;
        for (int i = 0; i < tab.length; i++)
        {
            if (i != zero) sum += (Math.abs(tab[i] - template[i]));
        }
        return sum;
    }


    private char intToChar(int move)
    {

        if (move == -1) return 'L';
        else if (move == 1) return 'R';
        else if (move == -4) return 'U';
        else if (move == 4) return 'D';
        return 0;
    }

    private int findZero(int[] tab)
    {
        return IntStream.range(0, tab.length).filter(i -> 0 == tab[i]).findFirst().orElse(-1);
    }






}
