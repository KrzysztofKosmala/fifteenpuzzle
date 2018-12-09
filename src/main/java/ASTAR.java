import java.util.*;
import java.util.stream.IntStream;


public class ASTAR extends Strategies
{
    private LinkedList<Node> frontier = new LinkedList<>();
    private HashMap<String, Node> explored = new HashMap<>();
    int countReccurencce = 0;
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
        ArrayList<Node> firstChoices=new ArrayList<>();
        while (!explored.containsKey(Arrays.toString(template)))
        {

            LinkedHashMap<Integer, Node> helper = makeMoves(frontier.peekFirst());

            countReccurencce = 0;

            Node obj = findWinner(helper);
            if(frontier.peekFirst()==firstNode)
            {
                firstChoices.add(obj);
            }
            parentInCurrnetNode = getParents(obj);

            obj.setParentCounter(parentInCurrnetNode);
            if (parentInCurrnetNode > maxDepthOfRecursion)
            {
                maxDepthOfRecursion = parentInCurrnetNode;
            }
            if (parentInCurrnetNode < 20 )
            {

                frontier.addFirst(obj);
                processedStates++;
                if (Arrays.equals(obj.getStateInNode(), template))
                {
                    solved = obj;
                    return true;
                }
                    explored.put(Arrays.toString(frontier.getLast().getStateInNode()), frontier.getLast());

                frontier.pollLast();
            } else
            {



                frontier.clear();
                frontier.add(firstNode);
                explored.clear();
                for(int i=0;i<firstChoices.size();i++)
                {
                    explored.put(Arrays.toString(firstChoices.get(i).getStateInNode()),firstChoices.get(i));
                }
            }
            helper.clear();

        }
        return false;
    }

    private LinkedHashMap<Integer, Node> makeMoves(Node node)
    {
        LinkedHashMap<Integer, Node> helper = new LinkedHashMap<>();
        int j;
        for (j = 0; j < 4; j++)
        {
            if (node.checkRange(howInt[j]))
            {


                if ((node.getNullLocation() + howInt[j]) != node.getParentNullLocation())
                {

                    Node nodeForHelper = new Node();

                    nodeForHelper.setParent(node);
                    nodeForHelper.setParentState(node.getStateInNode());
                    nodeForHelper.setParentNullLocation();
                    nodeForHelper.setStateInNode();
                    nodeForHelper.setNullLocation2();
                    nodeForHelper.setParentNullLocation();
                    nodeForHelper.setOperator(howCharOperator[j]);
                    nodeForHelper.move(howInt[j]);

                    if (!ifExistOnExplored(nodeForHelper.getStateInNode()))
                    {
                        if (Arrays.equals("hamm".toCharArray(), howChar))
                        {
                            nodeForHelper.setCost(getHammingDistance(nodeForHelper.getStateInNode()));
                        }
                        else if (Arrays.equals("manh".toCharArray(), howChar))
                        {
                            nodeForHelper.setCost(getManhattanDistance(nodeForHelper.getStateInNode()));
                        }

                        visitedStates++;
                        helper.put(j, nodeForHelper);
                    }

                }
            }
        }
        node.setFutureNullLocation2(1);
        return helper;


    }

    private boolean ifExistOnExplored(int[] i)
    {
        return explored.containsKey(Arrays.toString(i));
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
        int nullLocation = IntStream.range(0, tab.length).filter(i -> 0 == tab[i]).findFirst().orElse(-1);
        return nullLocation;
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

    private Node findWinner(LinkedHashMap<Integer, Node> helper)
    {
        countReccurencce++;
        int keyMinCost = findKeyOfMinCost(helper);

        int minCost = helper.get(keyMinCost).getCost();

        ArrayList<Integer> reiteration = findKeysOfReiteration(helper, minCost);


        if (reiteration.size() == 1 || countReccurencce > 3)
        {

            return helper.get(keyMinCost);
        } else
        {
            ArrayList<Integer> keysMinCost = new ArrayList<>();
            ArrayList<Integer> minCosts = new ArrayList<>();
            ArrayList<LinkedHashMap<Integer, Node>> helper1 = new ArrayList<>();
            for (int i = 0; i < reiteration.size(); i++)
            {
                helper1.add(makeMoves(helper.get(reiteration.get(i))));
                keysMinCost.add(findKeyOfMinCost(helper1.get(i)));
                minCosts.add(helper1.get(i).get(keysMinCost.get(i)).getCost());
            }
            int minimum = minCosts.get(0);
            int reiteration1 = 0;
            for (int i = 0; i < minCosts.size(); i++)
            {
                if (minCosts.get(i) < minimum)
                {
                    minimum = minCosts.get(i);
                }
            }
            for (int i = 0; i < minCosts.size(); i++)
            {
                if (minCosts.get(i) == minimum)
                {
                    reiteration1++;
                }
            }

            if (reiteration1 == 1)
            {

                return helper.get(reiteration.get(minCosts.indexOf(minimum)));


            } else
            {

                boolean flag = true;
                Node winner;
                int parents = 0;
                while (flag)
                {
                    ArrayList<Node> nodes = new ArrayList<>();
                    int minimum2 = 100;
                    int id = 0;
                    int count = 0;
                    for (int i = 0; i < helper1.size(); i++)
                    {
                        nodes.add(findWinner(helper1.get(i)));
                    }
                    for (int i = 0; i < nodes.size(); i++)
                    {
                        if (minimum2 > nodes.get(i).getCost())
                        {
                            minimum2 = nodes.get(i).getCost();
                            id = i;
                        }
                    }
                    for (int i = 0; i < nodes.size(); i++)
                    {
                        if (minimum2 == nodes.get(i).getCost())
                        {
                            count++;
                        }
                    }
                    if (count == 1 || countReccurencce > 4)
                    {
                        flag = false;
                        winner = nodes.get(id);

                        return winner.getParent();


                    } else
                    {
                        LinkedHashMap<Integer, Node> recurrence = new LinkedHashMap<>();

                        for (int i = 0; i < nodes.size(); i++)
                        {
                            recurrence.put(i, nodes.get(i));
                        }

                        winner = findWinner(recurrence);
                        return winner.getParent();
                    }

                }
            }
        }
        return null;
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


    private ArrayList<Integer> findKeysOfReiteration(LinkedHashMap<Integer, Node> helper, int minCost)
    {
        ArrayList<Integer> reiteration = new ArrayList<>();
        helper.entrySet().stream().forEach(entry ->
        {
            if (minCost == entry.getValue().getCost()) reiteration.add(entry.getKey());
        });
        return reiteration;
    }

    private int findKeyOfMinCost(LinkedHashMap<Integer, Node> helper)
    {
        int minCost = 100;
        int keyMinCost = 0;

        //1 znalezc minimum
        Iterator<Map.Entry<Integer, Node>> itr = helper.entrySet().iterator();
        while (itr.hasNext())
        {
            Map.Entry<Integer, Node> entry = itr.next();
            if (minCost > entry.getValue().getCost())
            {
                minCost = entry.getValue().getCost();
                keyMinCost = entry.getKey();
            }
        }
        return keyMinCost;
    }

    private boolean ifExistsOnExploredAdvanced(Node node)
    {
        if (explored.containsKey(Arrays.toString(node.getStateInNode())))
        {

            if (explored.get(Arrays.toString(node.getStateInNode())).getOperator() == node.getOperator()  )
            {
                return false;
            }
        }else
        {
            explored.remove(Arrays.toString(node.getStateInNode()));



        }
        return true;

    }
}
