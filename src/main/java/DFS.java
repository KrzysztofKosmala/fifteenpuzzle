import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class DFS extends Strategies
{
    private LinkedList<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();




    Node node;
    DFS(char[] how, int[] state, int rows, int columns)
    {
        super(how, rows, columns);
        tabCharToTabInt(how);
        Node first = makeFirstNode(state);
        node=first;
        frontier.addFirst(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);

    }
    @Override
    public void findSolution()
    {
        int x=0;
        while (!explored.containsKey(Arrays.toString(template)))//do ilości ustalonej nie do tego warunku!
            {
            int i=0;
            for (int j = 3; j >= 0; j--)
            {

                if (frontier.get(i).checkRange(howInt[j]))
                {
                    Node obj = new Node();


                    obj.setParentState(frontier.get(i).getStateInNode());
                    obj.setParent(frontier.get(i));
                    x++;
                    obj.setStateInNode();
                    obj.setNullLocation2();
                    obj.setParentNullLocation();
                    obj.setFutureNullLocation2(howInt[j]);

                    if (obj.getFutureNullLocation() != frontier.get(i).getNullLocation() )
                    {
                        obj.move(howInt[j]);

                        if (!ifExistsOnFrontier(obj.getStateInNode()) && !ifExistOnExplored(obj.getStateInNode()))
                        {
                            obj.setOperator(howChar[j]);
                            frontier.addFirst(obj);
                            x=0;


                            //   System.out.println(Arrays.toString(obj.getStateInNode()));
                            if (Arrays.equals(obj.getStateInNode(), template))
                            {
                                solved = obj;
                                find = true;
                                return ;
                            }
                            i++;
                        }
                    }
                    if(getParents(obj)-1>=30 || x>=3)
                    {
                        frontier.clear();
                        frontier.add(node);
                        x=0;

                        findSolution();

                    }
                }

            }
            if(!ifExistOnExplored(frontier.getFirst().getParentState()))
            {
                explored.put(Arrays.toString(frontier.getFirst().getParentState()), frontier.getFirst().getParent());

            }
                frontier.remove(frontier.getFirst().getParent());


        }

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

    private boolean ifExistOnExplored(int[] i)
    {
        return explored.containsKey(Arrays.toString(i));
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

    private int getParents(Node node)
    {
        parentsLine.clear();
        parentsLine.add(node);
        setFamilyLine();
        return parentsLine.size();
    }

}

