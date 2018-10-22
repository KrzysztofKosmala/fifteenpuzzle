

public abstract class Strategies
{
    protected int[] template ;
    protected int rows, columns;
    Strategies(int r, int c)
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
}
