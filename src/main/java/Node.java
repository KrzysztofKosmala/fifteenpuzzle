import java.util.Arrays;
import java.util.stream.IntStream;

public class Node
{
    private  int[] parentState ;
    private  int[] stateInNode ;
    private  int nullLocation;
    private int parentNullLocation;
    private int futureNullLocation=-1;
    boolean b=true;
    public void setParentNullLocation()
    {
        this.parentNullLocation = parent.getNullLocation();
    }

    private Node parent;
    private char operator;

    public int[] getStateInNode()
    {
        return stateInNode;
    }

    public  void setParentState(int[] tab)
    {
        parentState=tab;
    }
    public  int[] getParentState()
    {
        return parentState;
    }

    public int getFutureNullLocation()
    {
        return futureNullLocation;
    }

    public void setFutureNullLocation(int futureNullLocation)
    {
        this.futureNullLocation = futureNullLocation;
    }

    public  boolean checkRange(int T)
    {

        if(T==1)
        {
            if(nullLocation==3||nullLocation==7||nullLocation==11)
            {b=false; return b;}

        }else if(T==-1)
        {
            if(nullLocation==4||nullLocation==8||nullLocation==12)
            {b=false; return b;}
        }

        if(nullLocation+T < 0 || nullLocation+T >15 )
        {
            b=false;
            return b;
        }
        else
            {
               /* this.futureNullLocation=nullLocation+T;*/b=true;
                return b;
            }

    }

    public void setFutureNullLocation2(int T)
    {
        this.futureNullLocation=nullLocation+T;
    }

    public  void setNullLocation()
    {
        nullLocation = IntStream.range(0, stateInNode.length)
                                        .filter(i -> 0 == stateInNode[i])
                                        .findFirst()
                                        .orElse(-1);
    }
    public  void setNullLocation2()
    {
        nullLocation = parent.getNullLocation();
    }
    public Node()
    {

    }

    public int getNullLocation()
    {
        return nullLocation;
    }

    public void setStateInNode()
    {
        //if(state!=null)
        stateInNode = parentState.clone();
    }
    public void setStateInNode(int[] s)
    {
        //if(state!=null)
        stateInNode = s.clone();
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public void setOperator(char operator)
    {
        this.operator = operator;
    }

    public void move(int T)
    {

        stateInNode[nullLocation] = stateInNode[nullLocation + T];
        stateInNode[nullLocation + T] = 0;
                nullLocation = nullLocation + T;

    }

    public void setParentNullLocation2(int parentNullLocation)
    {
        this.parentNullLocation = parentNullLocation;
    }

    public char getOperator()
    {
        return operator;
    }
}
