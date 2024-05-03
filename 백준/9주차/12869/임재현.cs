using System;
using System.IO;

namespace 임재현
{
    class 임재현
    {
        static int[][] mutal = { new int[] { 9, 3, 1 }, new int[] { 9, 1, 3 }, new int[] { 3, 9, 1 }, new int[] { 3, 1, 9 }, new int[] { 1, 9, 3 }, new int[] { 1, 3, 9 } };
        static int[][][] dp;
        static int min;
        static void Main(string[] args)
        {
            StreamReader reader = new StreamReader(Console.OpenStandardInput());
            int N = int.Parse(reader.ReadLine());

            string[] tokens = reader.ReadLine().Split();
            int[] scv = new int[3];

            for (int i = 0; i < N; i++) scv[i] = int.Parse(tokens[i]);

            dp = new int[61][][];
            for (int i = 0; i < 61; i++)
            {
                dp[i] = new int[61][];
                for (int j = 0; j < 61; j++)
                {
                    dp[i][j] = new int[61];
                }
            }
            min = int.MaxValue;

            dfs(scv, 0);

            Console.WriteLine(min);
        }
        static void dfs(int[] scv, int cnt)
        {
            int s1 = scv[0];
            int s2 = scv[1];
            int s3 = scv[2];
         
            if (min <= cnt) return;

            if (dp[s1][s2][s3] != 0 && dp[s1][s2][s3] <= cnt) return;

            dp[s1][s2][s3] = cnt;

            if (s1 == 0 && s2 == 0 && s3 == 0)
            {
                min = Math.Min(min, cnt);
                return;
            }

            for (int i = 0; i < 6; i++)
            {
                dfs(new int[] { Math.Max(s1 - mutal[i][0], 0), Math.Max(s2 - mutal[i][1], 0), Math.Max(s3 - mutal[i][2], 0) }, cnt + 1);
            }
        }
    }
}
