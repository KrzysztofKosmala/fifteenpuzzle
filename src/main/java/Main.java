import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
       System.out.println("Hello World!");
        BFS s = new BFS(new char[]{'R','D','U','L'},new int[]{1,2,3,4,0,5,6,7,9,10,11,8,13,14,15,12});

        System.out.println(s.findSolution());


    }

}
/*
1  2  3   4
0  5  6   7
9  10 11  8
13 14 15  12*/