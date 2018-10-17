import java.util.*;

public class BFS extends Strategies
{
    private Queue<Node> frontier = new LinkedList<>();
    private HashMap<int[],Node> explored = new HashMap<>();
    private int[] howInt =  new int[4];
    private char[] howChar;

    /*liczniki list oraz glebokosci do dopisania!*/
    public BFS(char[] how, int[] state)
    {
        howChar=how.clone();
        charToInt(how);
        Node first = new Node();
        first.setStateInNode(state);
        first.setNullLocation();
        frontier.add(first);
        explored.put(first.getStateInNode(),first);


    }
    public boolean findSolution()//do testów
    {
        int j,i=0;
//
        while(!explored.containsKey(template))
        {

            for(j=0;j<4;j++)
            if(frontier.peek().checkRange(howInt[j]))
            {
                Node obj = new Node();

                obj.setParent(frontier.peek());
                obj.setParentState(frontier.peek().getStateInNode());
                obj.setStateInNode();
                obj.setNullLocation2();
                obj.move(howInt[j]);
                if (Arrays.equals(obj.getStateInNode(), template)){System.out.println("done");return true;}
                if(j==3)
                {
                    frontier.poll();
                }
                if(!frontier.contains(obj)||!explored.containsKey(obj.getStateInNode()))
                {
                    frontier.add(obj);

                    explored.put(obj.getStateInNode(),obj);
                    obj.setOperator(howChar[j]);

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




}
