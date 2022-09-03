package au.edu.utas.costing_tool.Util;

public class Log
{
    public static void log(Object o)
    {
        System.out.println("\n");
        System.out.println("================================================================================");
        System.out.println(o);
        System.out.println("================================================================================");
        System.out.println("\n");
    }

    public static void log(Object o, String s)
    {
        System.out.println("\n");
        System.out.println("================================================================================");
        if (s != null)
            System.out.println(s + ":");
        System.out.println(o);
        System.out.println("================================================================================");
        System.out.println("\n");
    }
}
