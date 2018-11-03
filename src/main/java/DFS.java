import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class DFS extends Strategies
{
    private LinkedList<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();

    DFS(char[] how, int[] state, int rows, int columns)
    {
        super( how, rows, columns);

        Node first = makeFirstNode(state);
        frontier.addFirst(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);

        
    }
    @Override
    public boolean findSolution()
    {
        while (!explored.containsKey(Arrays.toString(template)))//do ilości ustalonej nie do tego warunku!
        {
            int i=0;
            for (int j = 3; j >= 0; j--)
            {

                if (frontier.getFirst().checkRange(howInt[j]))
                {
                    Node obj = new Node();

                    obj.setParent(frontier.get(i));
                    obj.setParentState(frontier.get(i).getStateInNode());
                    obj.setStateInNode();
                    obj.setNullLocation2();
                    obj.setParentNullLocation();
                    obj.setFutureNullLocation2(howInt[j]);
                    if (obj.getFutureNullLocation() != frontier.get(i).getNullLocation())
                    {
                        obj.move(howInt[j]);


                    }
                    if (!ifExistsOnFrontier(obj.getStateInNode()) )
                    {
                        obj.setOperator(howChar[j]);


                        frontier.addFirst(obj);

                        //   System.out.println(Arrays.toString(obj.getStateInNode()));
                        if (Arrays.equals(obj.getStateInNode(), template))
                        {
                            solved = obj;
                            return true;
                        }
                    }
                    i++;
                }


            }
            if( !explored.containsKey(Arrays.toString(frontier.getFirst().getParentState())))
            {
                explored.put(Arrays.toString(frontier.getFirst().getParentState()), frontier.getFirst().getParent());
            }

            frontier.remove(frontier.getFirst().getParent());
        }
        return false;
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

    @Override
    protected int getAllStates()
    {
        return 0;//nie wiem
    }

    @Override
    protected int getProcessedStates()
    {
        return explored.size();
    }


}

