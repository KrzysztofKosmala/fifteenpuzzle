import java.util.*;

public class BFS extends Strategies
{
    private Queue<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();
    private ArrayList<Node> parentsLine = new ArrayList<>();
    private int rows,columns;//do dopisania wielowymiarowość!
    private int[] howInt =  new int[4];
    private char[] howChar;
    public int i=0;
    private Node solved=null;

    /*liczniki list oraz glebokosci do dopisania!*/
    BFS(char[] how, int[] state, int rows, int columns)
    {
        this.rows=rows;
        this.columns=columns;
        howChar=how.clone();
        charToInt(how);
        Node first = new Node();
        first.setStateInNode(state);
        first.setNullLocation();
        first.setParentNullLocation2(first.getNullLocation());
        frontier.add(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);
    }



    int getAllStates()
    {
        return explored.size();
    }

    int getProcessedStates()
    {
        return explored.size()-frontier.size();
    }

    public int getParentsCounter()
    {
        return parentsLine.size();
    }

    boolean findSolution()
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
                    if (obj.getFutureNullLocation() != frontier.peek().getNullLocation())
                    {
                        obj.move(howInt[j]);


                        if (!ifExistsOnFrontier(obj.getStateInNode()) || !explored.containsKey(Arrays.toString(obj.getStateInNode())))
                        {
                            obj.setOperator(howChar[j]);
                            System.out.println(i);

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

        while(parentsLine.get(parentsLine.size()-1).getParent()!=null)
        {
            setFamilyLineLoop(parentsLine.get(parentsLine.size()-1));
        }

    }

    private void setFamilyLineLoop(Node child)
    {
            Node help;
            help=child.getParent();
            parentsLine.add(help);
    }

    char[] getFamilyLine()
    {
        parentsLine.add(solved);
        setFamilyLine();

        String help;
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=parentsLine.size()-2; i>=0; i--)
        {
            stringBuilder.append(parentsLine.get(i).getOperator());
        }
        help = stringBuilder.toString();

        return help.toCharArray();
    }
}

