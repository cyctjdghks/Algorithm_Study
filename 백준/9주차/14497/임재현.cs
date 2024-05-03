using System;
using System.Collections.Generic;
using System.IO;
namespace _14497
{
    class 임재현
    {
        static int N, M;
        static int[,] map;
        static bool[,] visited;
        static void Main(string[] args)
        {
            StreamReader reader = new StreamReader(Console.OpenStandardInput());
            string inputLine = reader.ReadLine();
            string[] tokens = inputLine.Split();
            N = int.Parse(tokens[0]);
            M = int.Parse(tokens[1]);
            map = new int[N, M];
            visited = new bool[N, M];

            inputLine = reader.ReadLine();
            tokens = inputLine.Split();
            int sy = int.Parse(tokens[1]) - 1;
            int sx = int.Parse(tokens[0]) - 1;
            int ey = int.Parse(tokens[3]) - 1;
            int ex = int.Parse(tokens[2]) - 1;

        }
        
    }
}
