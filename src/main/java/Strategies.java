import java.util.ArrayList;
import java.util.Arrays;

public abstract class Strategies
{
    Node solved=null;
    int[] template ;
    char[] howChar;
    int[] howInt =  new int[4];

    private int rows, columns;
    private ArrayList<Node> parentsLine = new ArrayList<>();



    Strategies(char[] how, int r, int c)
    {
        setTemplate(r,c);
        howChar=how.clone();


    }



    public abstract boolean findSolution();
    public int getParentsCounter()

    {
        return parentsLine.size();
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


    protected abstract boolean ifExistsOnFrontier(int[] i );
    protected abstract int getAllStates();
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
    void charToInt(char[] how)
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

}
