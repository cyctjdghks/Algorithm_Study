using System;
using System.Collections.Generic;
using System.IO;
namespace _13913
{
    class 임재현
    {
        static readonly int[] dx = new int[] { 0, -1, 1 };
        static int k;
        static int n;

        static Stack<int> stack = new Stack<int>();

        static void Main(string[] args)
        {
            StreamReader reader = new StreamReader(Console.OpenStandardInput());
            string inputLine = reader.ReadLine();
            string[] tokens = inputLine.Split();
            int N = int.Parse(tokens[0]);
            int K = int.Parse(tokens[1]);

            bool[] visit = new bool[100001];
            int[] parent = new int[100001];

            Queue<Position> q = new Queue<Position>();
            q.Enqueue(new Position(N, 0));
            visit[N] = true;

            while (q.Count > 0)
            {
                Position temp = q.Dequeue();

                if (temp.current == K)
                {
                    Console.WriteLine(temp.time);

                    Stack<int> stack = new Stack<int>();
                    int a = temp.current;
                    while (a != N)
                    {
                        stack.Push(a);
                        a = parent[a];
                    }
                    stack.Push(a);

                    while (stack.Count > 0)
                    {
                        Console.Write(stack.Pop() + " ");
                    }

                    break;
                }

                if (temp.current + 1 < 100001 && !visit[temp.current + 1])
                {
                    q.Enqueue(new Position(temp.current + 1, temp.time + 1));
                    visit[temp.current + 1] = true;
                    parent[temp.current + 1] = temp.current;
                }

                if (temp.current - 1 >= 0 && !visit[temp.current - 1])
                {
                    q.Enqueue(new Position(temp.current - 1, temp.time + 1));
                    visit[temp.current - 1] = true;
                    parent[temp.current - 1] = temp.current;
                }

                if (temp.current * 2 < 100001 && !visit[temp.current * 2])
                {
                    q.Enqueue(new Position(temp.current * 2, temp.time + 1));
                    visit[temp.current * 2] = true;
                    parent[temp.current * 2] = temp.current;
                }
            }
        }
    }
    class Position
    {
        public int current;
        public int time;

        public Position(int current, int time)
        {
            this.current = current;
            this.time = time;
        }
    }
}

