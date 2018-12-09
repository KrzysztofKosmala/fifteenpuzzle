import java.lang.reflect.Array;
import java.util.stream.IntStream;

public class Node
{
    private static int rows, columns;
    private static int[] borders;

    private int[] parentState ;
    private int[] stateInNode ;
    private int nullLocation;



    private int parentNullLocation;
    private int futureNullLocation=-1;

    private int parentCounter;

    private char operator;

    private Node parent;
//tylko na potrzeby astar
    private int cost=-1;
    Node()
    { }


    public int getCost()
    {
        return cost;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public void setParentCounter(int parentCounter)
    {
        this.parentCounter = parentCounter;
    }
    public int getParentNullLocation()
    {
        return parentNullLocation;
    }
    public void setFutureNullLocation(int futureNullLocation)
    {
        this.futureNullLocation = futureNullLocation;
    }
    public int getParentCounter() {
        return parentCounter;
    }

    void setBorders()
    {
        borders= new int[columns-1];
        int r=rows-1;
        borders[0]=r;
        for(int i=1; i<columns-1; i++)
        {
            borders[i]=borders[i-1]+rows;
        }
    }
    void setRowsAndColumns(int r,int c)
    {
        rows = r;
        columns = c;
    }
    void setParentNullLocation()
    {
        this.parentNullLocation = parent.getNullLocation();
    }
    void setParentState(int[] tab)
    {
        parentState=tab;
    }
    void setFutureNullLocation2(int T)
    {
        this.futureNullLocation=nullLocation+T;
    }
    void setNullLocation2()
    {
        nullLocation = parent.getNullLocation();
    }
    void setNullLocation()
    {
        nullLocation = IntStream.range(0, stateInNode.length)
                .filter(i -> 0 == stateInNode[i])
                .findFirst()
                .orElse(-1);
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

    boolean checkRange(int T)
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

    int[] getParentState()
    {
        return parentState;
    }
    int[] getStateInNode()
    {
        return stateInNode;
    }
    int getFutureNullLocation()
    {
        return futureNullLocation;
    }
    int getNullLocation()
    {
        return nullLocation;
    }

    char getOperator()
    {
        return operator;
    }

    Node getParent()
    {
        return parent;
    }

}
