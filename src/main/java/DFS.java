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
    public boolean findSolution()
    {
        parentInCurrnetNode=0;
        int x=0;
        while (!explored.containsKey(Arrays.toString(template)))//do ilości ustalonej nie do tego warunku!
            {
                int i=0;
            /*Ta pętla towrzy wszystkie możliwe ruchy dla danego stanu np. jeśli kolejność przeszukiwania to RDUL a zero
             nie wychodząc poza układanke i nie wchodząc na miejsce z którego przyszło może przesunąć sie
            tylko w D i U to ta pętla stworzy takie obiekty i wrzuci je w odwrotnej kolejności na frontier
            żeby następny przeszukiwany stan był ostatnim dzieckiem rodzica zgodnie z harakterystyką przeszukiwania metodą DFS*/
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
                                if (Arrays.equals(obj.getStateInNode(), template))
                                {
                                    solved = obj;
                                    parentInCurrnetNode=getParents(obj);
                                    return true;
                                }
                                i++;
                            }
                        }
                    }
                }
                parentInCurrnetNode=getParents(frontier.getFirst());

                /*
                Jeśli utowrzony stan należy do pokolenia n(czyt. do maksymalnej możliwej liczby pokoleń rodziców określonej w zadaniu)
                 to jego rodzic zostaje uznany jako przeszukany i trafia na explored a stan wraz ze wszystkimi innymi wyprodukowanymi
                 przez tego samoego rodzica zostają usunięte
                 */
                if(parentInCurrnetNode>=4 && i!=0)
                {
                    if (!ifExistOnExplored(frontier.getFirst().getParentState()))
                    {
                        explored.put(Arrays.toString(frontier.getFirst().getParentState()), frontier.getFirst().getParent());
                    }
                    frontier.remove(frontier.getFirst().getParent());

                    for(int k=0; k<i; k++)
                        frontier.remove(0);


                }
                /*
                Jeśli jakiś stan nie może wykonać ruchu bo skończyly sie możliwe do wykonania to taki stan uznaje sie za przeszukany
                trafia do explored i zostaje usunięty
                */
                else if(i==0)
                {
                    if (!ifExistOnExplored(frontier.getFirst().getParentState()))
                    {
                        explored.put(Arrays.toString(frontier.getFirst().getStateInNode()), frontier.getFirst());
                    }
                    frontier.removeFirst();
                }
                /*
                w pozostałych przypadkach rodzic po wyprodukowaniu wszystkich swoich ruchów zostaje uznany za przeszukany i trafia na expolred
                */
                else
                {

                    if (!ifExistOnExplored(frontier.getFirst().getParentState()))
                    {

                        explored.put(Arrays.toString(frontier.getFirst().getParentState()), frontier.getFirst().getParent());

                    }

                        frontier.remove(frontier.getFirst().getParent());


                }parentInCurrnetNode = 0;
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



}

