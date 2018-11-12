import java.util.*;
import java.util.stream.IntStream;


public class ASTAR extends Strategies
{
    private LinkedList<Node> frontier = new LinkedList<>();
    private HashMap<String,Node> explored = new HashMap<>();

    private int[] howInt; // ustawione na sztywno bo kolejność nie ma znaczenia,
                                                // i tak algorytm wybierze najbardziej opłacalne przejście

    class InnerStateAndMove
    {
        Integer[] intTab;
        int move;

        public InnerStateAndMove(Integer[] intTab, int move)
        {
            this.intTab = intTab;
            this.move = move;
        }

        public Integer[] getIntTab()
        {
            return intTab;
        }

        public void setIntTab(Integer[] intTab)
        {
            this.intTab = intTab;
        }

        public int getMove()
        {
            return move;
        }

        public void setMove(int move)
        {
            this.move = move;
        }
    }


    private char[] howCharHeuristic;
    ASTAR(char[] how, int[] state, int rows, int columns)
    {
        super( how, rows, columns);

        Node first = makeFirstNode(state);
        frontier.add(first);
        explored.put(Arrays.toString(first.getStateInNode()),first);
        howCharHeuristic = new char[]{'R','D','U','L'};
        howInt = new int[]{1,4,-4,1};
    }



    @Override
    public boolean findSolution()
    {



            if(Arrays.equals(new String("hamm").toCharArray(),howChar))
            {
                int j;
                while(!explored.containsKey(Arrays.toString(template)))//do ilości ustalonej nie do tego warunku!
                {
                    int price = 0;
                    LinkedHashMap<Integer, InnerStateAndMove> helper = new LinkedHashMap<>();
                    for (j = 0; j < 4; j++)
                    {
                        if (frontier.peek().checkRange(howInt[j]))
                        {
                            helper.put(
                                        getHammingDistance(Arrays.stream(hypotheticalMove(frontier.peek().getStateInNode(),
                                                frontier.peek().getNullLocation(), howInt[j])).mapToInt(Integer::intValue).toArray()),
                                    new InnerStateAndMove(hypotheticalMove(frontier.peek().getStateInNode(), frontier.peek().getNullLocation(), howInt[j]),howInt[j])

                                        );

                        }

                    }
                    j--;
                    Map.Entry<Integer, InnerStateAndMove> minEntry = helper.entrySet().stream()
                                                            .min(Map.Entry.comparingByKey()).get();

                    Node obj = new Node();

                    obj.setParent(frontier.peek());
                    obj.setParentState(frontier.peek().getStateInNode());
                    obj.setStateInNode(Arrays.stream(minEntry.getValue().getIntTab()).mapToInt(Integer::intValue).toArray());
                    obj.setNullLocation();
                    obj.setParentNullLocation();
                    obj.setOperator(intToChar(minEntry.getValue().getMove()));


                    if(!ifExistsOnFrontier(obj.getStateInNode()) && !ifExistOnExplored(obj.getStateInNode()))
                    frontier.addFirst(obj);



                    if (Arrays.equals(obj.getStateInNode(), template))
                    {
                        solved=obj;
                        return true;
                    }if(j==3)
                    {
                        explored.put(Arrays.toString(frontier.getLast().getStateInNode()),frontier.getLast());
                        frontier.pollLast();

                    }
                    j=0;
                }

                return false;

            }else if(howChar.equals("manh"))
            {



            return true;

            }
            return false;

    }

    private boolean ifExistOnExplored(int[] i)
    {
        return explored.containsKey(Arrays.toString(i));
    }
    private Integer getHammingDistance(int[] tab)
    {
        int a =0;

        int nullLocation = IntStream.range(0, tab.length)
                .filter(i -> 0 == tab[i])
                .findFirst()
                .orElse(-1);

        for(int i=0; i<tab.length; i++)
        {
            if(tab[i]!=template[i] &&  i!=nullLocation)
            {
                a++;
            }
        }

        return new Integer(a);
    }


    private Integer[] hypotheticalMove(int[] state,int nullLocation, int move)
    {
        int[] innerState = state.clone();
        innerState[nullLocation] = innerState[nullLocation + move];
        innerState[nullLocation + move] = 0;
       // nullLocation = nullLocation + move;

        return  Arrays.stream( innerState ).boxed().toArray( Integer[]::new );
    }
    private char intToChar(int move)
    {

            if(move==-1)
                return 'L';
            else if(move==1)
                return 'R';
            else if(move==-4)
                return 'U';
            else if(move==4)
                return 'D';
        return 0;
    }


    @Override
    protected boolean ifExistsOnFrontier(int[] i)
    {
        for( Node a : frontier)
        {
            if( Arrays.equals(a.getStateInNode(),i))
                return true;

        }
        return false;
    }

    @Override
    protected int getAllStates() {
        return 0;
    }

    @Override
    protected int getProcessedStates() {
        return 0;
    }


}
