
/**
 * Write a description of class RankingMatchup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RankingMatchup
{
    public static int[] oneMatchup(double[][] probabilities)
    {
        int[] winnings = new int[probabilities.length];
        for(int team1 = 0; team1 < probabilities.length; team1++)
        {
            for(int team2 = 0; team2 < probabilities.length; team2++)
            {
                if(team1 != team2)
                {
                    if(Math.random() < probabilities[team1][team2])
                    {
                        winnings[team1]++;
                    }
                    else
                    {
                        winnings[team2]++;
                    }
                }

            }
        }
        return winnings;
    }

    public static double[][] placement(int[] winnings)
    {
        double[][] placements = new double[winnings.length][winnings.length];

        //sort teams and winnings into numerical order
        int[] teams = new int[winnings.length];
        for(int i = 0; i < winnings.length; i++)
        {
            teams[i] = i;
        }
        for (int i = 0; i < winnings.length; i++)
        {
            for(int x = 0; x < winnings.length -i-1; x++)
            {
                if(winnings[x] > winnings[x+1])
                {
                    int temporary = winnings[x];
                    winnings[x] = winnings[x+1];
                    winnings[x+1] = temporary;

                    temporary = teams[x];
                    teams[x] = teams[x+1];
                    teams[x+1] = temporary;
                }
            }
        }
        //end of sort

        int count = 1;
        int current = winnings[0]; 
        int previous=-1;
        for(int i =0; i <winnings.length; i++)
        {
            if (current == previous)
            {
                count++;
            }
            else if (count == 1)
            {
                placements[teams[i]][winnings.length-i-1]+=1.0;
            }
            else
            {
                for(int teamNumber = 0; teamNumber < count;teamNumber++)
                {
                    for(int placementNumber = 0; placementNumber < count; placementNumber++)
                    {
                        placements[teams[i-count+teamNumber]][winnings.length-i+count-1-placementNumber] += 1.0/count;
                    }}
            }
        }
        return placements;
    }gh

    public static void main(double[][] probabilities)
    {
        double[][] placements = new double[probabilities.length][probabilities[0].length];
        double[][] placementsCopy = new double[probabilities.length][probabilities[0].length];
        for(int trials = 0; trials < 10000; trials++)
        {
            placementsCopy = placement(oneMatchup(probabilities));
            for (int i =0; i < probabilities.length;i++)
            {
                for(int j =0; j < probabilities[0].length; j++)
                {
                    placements[i][j] += placementsCopy[i][j];
                }
            }
        }
        for (int i =0; i < probabilities.length;i++)
            {
                for(int j =0; j < probabilities[0].length; j++)
                {
                    System.out.print(placements[i][j] + " ");
                }
                System.out.println();
            }
    }
    
    public static void main(String[] args)
    {
        double[][] array = new double[3][3];
        array[0][0] = 0;
        array[1][1] = 0;
        array[2][2] = 0;
        array[0][1] = .2;
        array[0][2] = .3;
        array[1][0] = .8;
        array[1][2] = .6;
        array[2][0] = .7;
        array[2][1] = .4;
        
        main(array);
    }

    
    
}
