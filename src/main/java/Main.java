public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        BFS s = new BFS(new char[]{'R','D','U','L'},new int[]{1,2,3,4,5,6,7,8,9,10,0,11,13,14,15,12});
        boolean i = s.findSolution();

    }
}
