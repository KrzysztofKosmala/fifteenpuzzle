import java.util.Arrays;
import java.util.stream.IntStream;

public class Node
{
    private  int[] parentState ;
    private  int[] stateInNode ;
    private  int nullLocation;
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

    public  boolean checkRange(int T)
    {

        switch (T)
        {
            case     1: if(nullLocation==3||nullLocation==7||nullLocation==11) return false;
            case    -1: if(nullLocation==4||nullLocation==8||nullLocation==12) return false;
        }

            if(nullLocation+T < 0 ||nullLocation+T >15)
            return false;
        else return true;

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

    public char getOperator()
    {
        return operator;
    }
}
