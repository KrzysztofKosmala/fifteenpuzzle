import java.util.ArrayList;
import java.util.Arrays;

public abstract class Strategies
{
    protected int[] template ;
    protected int rows, columns;
    protected char[] howChar;
    protected int[] howInt =  new int[4];
    protected ArrayList<Node> parentsLine = new ArrayList<>();
    protected Node solved=null;
    Strategies(char[] how, int r, int c)
    {
        setTemplate(r,c);
        howChar=how.clone();
        charToInt(how);

    }


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
    private void charToInt(char[] how)
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
    protected Node makeFirstNode(int[] state)
    {
        Node first = new Node();
        first.setRowsAndColumns(rows,columns);
        first.setStateInNode(state);
        first.setNullLocation();
        first.setBorders();
        first.setParentNullLocation2(first.getNullLocation());
        return first;
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
    protected abstract   boolean ifExistsOnFrontier(int[] i );

    public abstract boolean findSolution();

    protected abstract int getAllStates();

    protected abstract int getProcessedStates();

    public int getParentsCounter()

    {
        return parentsLine.size();
    }
}
