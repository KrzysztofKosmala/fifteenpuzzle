import java.util.*;

public class BFS extends Strategies
{
    private Queue<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();
    private ArrayList<Node> parentsLine = new ArrayList<>();
    private int[] howInt =  new int[4];
    private char[] howChar;
    private int movesCounter=0;
    private int allStates;
    private int processedStates;
    private int parentsCounter=0;
    public int i=0;
    private Node solved=null;

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

    public boolean findSolution()
    {
        int j;
        while(!explored.containsKey(Arrays.toString(template)))
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
                    if (obj.getFutureNullLocation() != frontier.peek().getNullLocation())
                    {
                        obj.move(howInt[j]);
                        movesCounter++;

                        if (!ifExistsOnFrontier(obj.getStateInNode()) || !explored.containsKey(Arrays.toString(obj.getStateInNode())))
                        {
                            obj.setOperator(howChar[j]);
                            System.out.println(i);
                            parentsCounter++;
                            frontier.add(obj);
                            explored.put(Arrays.toString(obj.getStateInNode()), obj);
                            System.out.println(Arrays.toString(obj.getStateInNode()));
                            if (Arrays.equals(obj.getStateInNode(), template))
                            {
                                solved=obj;
                                System.out.println("done");return true;
                            }
                        }
                    }

                        }
                    i++;
                }
                if (j >= 3)
                {
                    frontier.poll();
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

    private void setFamilyLine()
    {

        while(parentsLine.get(parentsLine.size()).getParent()!=null)
        {
            setFamilyLineLoop(parentsLine.get(parentsLine.size()));
        }

    }

    private void setFamilyLineLoop(Node child)
    {
            Node help;
            help=child.getParent();
            parentsLine.add(help);
    }

    public char[] getFamilyLine()
    {
        parentsLine.add(solved);
        setFamilyLine();

        String help;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=parentsLine.size(); i==0; i--)
        {
            stringBuilder.append(parentsLine.get(i).getOperator());
        }
        help = stringBuilder.toString();

        return help.toCharArray();
    }
}

