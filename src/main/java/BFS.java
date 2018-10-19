import java.util.*;

public class BFS extends Strategies
{
    private Queue<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();
    private int[] howInt =  new int[4];
    private char[] howChar;
    private int movesCounter=0;
    private int allStates;
    private int processedStates;
    private int parentsCounter=0;
    public int i=0;

    /*liczniki list oraz glebokosci do dopisania!*/
    public BFS(char[] how, int[] state)
    {
        howChar=how.clone();
        charToInt(how);
        Node first = new Node();
        first.setStateInNode(state);
        first.setNullLocation();
        first.setParentNullLocation2(first.getNullLocation());

        frontier.add(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);
    }

    public int getMovesCounter()
    {
        return movesCounter;
    }

    public int getAllStates()
    {
        return explored.size();
    }

    public int getProcessedStates()
    {
        return explored.size()-frontier.size();
    }

    public int getParentsCounter()
    {
        return parentsCounter;
    }

    public boolean findSolution()//do testów
    {
        int j;
        while(!explored.containsKey(Arrays.toString(template)))
        {

            for(j=0;j<4;j++)
            if(frontier.peek().checkRange(howInt[j]))
            {
                Node obj = new Node();

                obj.setParent(frontier.peek());
                obj.setParentState(frontier.peek().getStateInNode());
                obj.setStateInNode();
                obj.setNullLocation2();
                obj.setParentNullLocation();
                if(obj.getFutureNullLocation()!=frontier.peek().getNullLocation())
                {
                    obj.move(howInt[j]);
                    movesCounter++;

                }
                if(j==3)
                {
                    frontier.poll();
                }

                if(!ifExistsOnFrontier(obj.getStateInNode())||!explored.containsKey(Arrays.toString(obj.getStateInNode())))
                {System.out.println(i);
                    parentsCounter++;
                    frontier.add(obj);
                    explored.put(Arrays.toString(obj.getStateInNode()),obj);
                    obj.setOperator(howChar[j]);
                    if (Arrays.equals(obj.getStateInNode(), template)){System.out.println("done");return true;}
                }



                i++;
            }
        }return false;
    }

    private void charToInt(char[] how)
    {
        for (int i=0; i<4; i++)
        {
            if(how[i]=='R')
            {
                howInt[i]=1;
            }else if(how[i]=='D')
            {
                howInt[i]=4;
            }else if(how[i]=='U')
            {
                howInt[i]=-4;
            }else if(how[i]=='L')
            {
                howInt[i]=-1;
            }
            //jakis wyjatek
        }

    }

    private boolean ifExistsOnFrontier(int[] i )
        {
           for( Node a : frontier)
           {
               if( Arrays.equals(a.getStateInNode(),i))
                   return true;

           }
           return false;
        }



}
