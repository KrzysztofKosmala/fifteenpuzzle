import java.util.Arrays;
import java.util.stream.IntStream;

public class Node
{
    private  int[] parentState ;
    private  int[] stateInNode ;
    private  int nullLocation;
    private int parentNullLocation;
    private int futureNullLocation=-1;
    private int parentCounter;
    private Node parent;
    private char operator;
    private static int rows, columns;
    private static int[] borders;

    public void setBorders()
    {
        borders= new int[columns-1];
        int r=rows-1;
        borders[0]=r;
        for(int i=1; i<columns-1; i++)
        {
            borders[i]=borders[i-1]+rows;
        }
    }
    public void setRowsAndColumns(int r,int c)
    {
        rows = r;
        columns = c;
    }

    Node()
    {

    }

    void setParentNullLocation()
    {
        this.parentNullLocation = parent.getNullLocation();
    }

    int[] getStateInNode()
    {
        return stateInNode;
    }

    void setParentState(int[] tab)
    {
        parentState=tab;
    }

    public  int[] getParentState()
    {
        return parentState;
    }

    int getFutureNullLocation()
    {
        return futureNullLocation;
    }

    public void setFutureNullLocation(int futureNullLocation)
    {
        this.futureNullLocation = futureNullLocation;
    }

    Node getParent()
    {
        return parent;
    }

    boolean checkRange(int T)//do przerobki
    {

        if(T==1)
        {
            for(int i=0; i<columns-1; i++)
            {
                if(borders[i]==nullLocation)
                    return false;
            }
        }else if(T==-1)
        {
            for(int i=0; i<columns-1; i++)
            {
                if(borders[i]+1==nullLocation)
                    return false;
            }
        }

        return nullLocation + T >= 0 && nullLocation + T <= rows*columns-1;

    }

    void setFutureNullLocation2(int T)
    {
        this.futureNullLocation=nullLocation+T;
    }

    void setNullLocation()
    {
        nullLocation = IntStream.range(0, stateInNode.length)
                                        .filter(i -> 0 == stateInNode[i])
                                        .findFirst()
                                        .orElse(-1);
    }

    void setNullLocation2()
    {
        nullLocation = parent.getNullLocation();
    }

    int getNullLocation()
    {
        return nullLocation;
    }

    void setStateInNode()
    {
        //if(state!=null)
        stateInNode = parentState.clone();
    }
    void setStateInNode(int[] s)
    {
        //if(state!=null)
        stateInNode = s.clone();
    }

    public int getParentCounter() {
        return parentCounter;
    }

    void setParent(Node parent)
    {
        parentCounter++;
        this.parent = parent;
    }

    void setOperator(char operator)
    {
        this.operator = operator;
    }

    void move(int T)
    {

        stateInNode[nullLocation] = stateInNode[nullLocation + T];
        stateInNode[nullLocation + T] = 0;
                nullLocation = nullLocation + T;

    }

    void setParentNullLocation2(int parentNullLocation)
    {
        this.parentNullLocation = parentNullLocation;
    }

    char getOperator()
    {
        return operator;
    }
}
