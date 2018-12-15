import java.util.ArrayList;

public abstract class Strategies
{
    Node solved=null;
    int[] template ;
    char[] howChar;
    int[] howInt =  new int[4];

    private int rows, columns;
    ArrayList<Node> parentsLine = new ArrayList<>();

    int parentInCurrnetNode=0;
    int processedStates=0;
    int visitedStates=0;
    int maxDepthOfRecursion=0;


    Strategies(char[] how, int r, int c)
    {
        setTemplate(r,c);
        howChar=how.clone();


    }

    public abstract boolean findSolution();

    Node makeFirstNode(int[] state)
    {
        Node first = new Node();
        first.setRowsAndColumns(rows,columns);
        first.setStateInNode(state);
        first.setNullLocation();
        first.setBorders();
        first.setParentNullLocation2(first.getNullLocation());
        return first;
    }

    void tabCharToTabInt(char[] how)
    {
        for (int i=0; i<4; i++)
        {
            if(how[i]=='R')
            {
                howInt[i]=1;
            }else if(how[i]=='D')
            {
                howInt[i]=rows;
            }else if(how[i]=='U')
            {
                howInt[i]=-rows;
            }else if(how[i]=='L')
            {
                howInt[i]=-1;
            }
            //jakis wyjatek
        }

    }

    protected abstract boolean ifExistsOnFrontier(int[] i );


    //getter & setter
    public Node getSolved()
    {
        return solved;
    }
    protected abstract int getVisitedStates();
    protected abstract int getProcessedStates();
    private void setTemplate(int r, int c)
    {
        this.rows=r;
        this.columns=c;
        template = new int[r*c];
        for(int i=0;i<r*c; i++)
        {
            template[i]=i+1;
        }
        template[r*c-1]=0;
    }
    void setFamilyLine()
    {

        while(parentsLine.get(parentsLine.size()-1).getParent()!=null)
        {
            setFamilyLineLoop(parentsLine.get(parentsLine.size()-1));
        }

    }
    void setFamilyLineLoop(Node child)
    {
        Node help;
        help=child.getParent();
        parentsLine.add(help);
    }
    int getParents(Node node)
    {
        parentsLine.clear();
        parentsLine.add(node);
        setFamilyLine();
        return parentsLine.size()-1;
    }
    char[] getFamilyLine()
    {
        parentsLine.clear();
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
    public ArrayList<Node> getParentsLine(Node node)
    {
        parentsLine.clear();
        parentsLine.add(node);
        setFamilyLine();
        return parentsLine;
    }
    int getMaxDepthOfRecursion()
    {
        return maxDepthOfRecursion;
    }
    public int getParentsCounter()

    {
        return parentsLine.size();
    }
    public ArrayList<Node> getParentsLine()
    {
        return parentsLine;
    }


}
