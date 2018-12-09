import java.util.*;

public class BFS extends Strategies
{
    private Queue<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();




    public int i=0;



    BFS(char[] how, int[] state, int rows, int columns)
    {
        super( how, rows, columns);
        tabCharToTabInt(how);
        Node first = makeFirstNode(state);
        frontier.add(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);
    }



    @Override
    public int getVisitedStates()
    {
        return visitedStates;
    }
    @Override
    public int getProcessedStates()
    {
        return explored.size()-frontier.size();
    }
    @Override
    public boolean findSolution()
    {
        int j;
        while(!explored.containsKey(Arrays.toString(template)))//do ilości ustalonej nie do tego warunku!
        {

            for(j=0;j<4;j++)
            {
                if (frontier.peek().checkRange(howInt[j]))
                {
                    Node obj = new Node();

                    obj.setParent(frontier.peek());
                    obj.setParentState(frontier.peek().getStateInNode());
                    obj.setStateInNode();
                    obj.setNullLocation2();
                    obj.setParentNullLocation();
                    obj.setFutureNullLocation2(howInt[j]);
                    if (obj.getFutureNullLocation() != frontier.peek().getParentNullLocation())
                    {
                        obj.move(howInt[j]);


                        if (!ifExistsOnFrontier(obj.getStateInNode()) || !explored.containsKey(Arrays.toString(obj.getStateInNode())))
                        {
                            obj.setOperator(howChar[j]);

                            visitedStates++;
                            frontier.add(obj);
                            explored.put(Arrays.toString(obj.getStateInNode()), obj);

                            if (Arrays.equals(obj.getStateInNode(), template))
                            {
                                solved=obj;

                                parentInCurrnetNode=getParents(obj);
                                return true;
                            }
                        }
                    }

                        }
                    i++;
                }
            frontier.poll();
        }return false;
    }
    @Override
    protected boolean ifExistsOnFrontier(int[] i )
    {
        for( Node a : frontier)
        {
            if( Arrays.equals(a.getStateInNode(),i))
                return true;

        }
        return false;
    }





}

