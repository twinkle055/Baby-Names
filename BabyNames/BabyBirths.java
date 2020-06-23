
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    
    public void totalBirths(){
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalNames = 0;
        int totalBoys = 0;
        int totalGN = 0;
        int totalGirls = 0;
        int totalBN = 0; 
        for(CSVRecord record : fr.getCSVParser(false))
        {
            totalNames += 1;
            totalBirths += Integer.parseInt(record.get(2));
            if(record.get(1).equals("M"))
            {
                totalBoys += Integer.parseInt(record.get(2));
                totalBN += 1; 
            }
            else
            {
                totalGirls += Integer.parseInt(record.get(2));
                totalGN += 1;
            }        
        }
        System.out.println("Total Girls = " + totalGirls);
        System.out.println("Total Girl's Names = " + totalGN);
        System.out.println("Total Boys = " + totalBoys);
        System.out.println("Total Boy's Names = " + totalBN);
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Total Birth Names = " + totalNames);
    }
    
    public int getRank(int year, String name, String gender){
        String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames_small/testing/yob" + year + "short.csv";
        File f = new File(fs);
        FileResource fr = new FileResource(f);
        //FileResource fr = new FileResource();
        int countF = 0;
        int countM = 0;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals("F"))
                countF += 1;
            else
                countM += 1;
            if(rec.get(1).equals(gender) && rec.get(0).equals(name))
            {
                if(rec.get(1).equals("F"))
                    return countF;
                else
                    return countM;
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames_small/testing/yob" + year + "short.csv";
        File f = new File(fs);
        FileResource fr = new FileResource(f);
        //FileResource fr = new FileResource();
        int countF = 0;
        int countM = 0;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals("F"))
                countF += 1;
            else
                countM += 1;
            if(rank == countF && gender.equals("F"))
                return rec.get(0);
            else if(rank == countM && gender.equals("M"))
                return rec.get(0);
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames_small/testing/yob" + year + "short.csv";
        File f = new File(fs);
        FileResource fr = new FileResource(f);
        //FileResource fr = new FileResource();
        String nfs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames/us_babynames_by_year/yob" + newYear + ".csv";
        //String nfs = "F:Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames_small/testing/yob" + newYear + "short.csv";
        File nf = new File(nfs);
        FileResource nfr = new FileResource(nf);
        //FileResource nfr = new FileResource();
        int rankF = 0;
        int rankM = 0;
        int rank = -1; 
        String newName = null;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals("F"))
                rankF += 1;
            else
                rankM += 1;
            if(rec.get(0).equals(name) && gender.equals("F"))
            {
                rank = rankF;
                break;
            }
            else if(rec.get(0).equals(name) && gender.equals("M"))
            {
                rank = rankM;
                break;
            }
        }
        if(rank == -1)
            return;
        int nRankF = 0;
        int nRankM = 0;
        for(CSVRecord rec : nfr.getCSVParser(false))
        {
            if(rec.get(1).equals("F"))
                nRankF += 1;
            else
                nRankM += 1;
            if(rank == nRankF && gender.equals("F"))
            {
                newName = rec.get(0);
                break;
            }
            else if(rank == nRankM && gender.equals("M"))
            {
                newName = rec.get(0);
                break;
            }
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank;
        int highRank = -1;
        int yearOHR = -1;
        for(File f : dr.selectedFiles())
        {
            String filename = f.getName();
            String y = filename.substring(3, 7);
            int year = Integer.parseInt(y);
            rank = getRank(year, name, gender); 
            if(yearOHR == -1 && rank != -1){
                highRank = rank;
                yearOHR = year;
            }
            else if(rank < highRank && rank != -1)
            {
                highRank = rank;
                yearOHR = year;
            }
        }
        System.out.println("Highest Rank : " + highRank);
        return yearOHR;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double avg = 0;
        int rank = 0;
        int count = 0;
        for(File f : dr.selectedFiles())
        {
            count += 1;
            String filename = f.getName();
            String y = filename.substring(3, 7);
            int year = Integer.parseInt(y);
            rank = getRank(year, name, gender);
            avg += rank;  
        }
        avg = avg/count;
        return avg;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames/us_babynames_by_year/yob" + year + ".csv";
        //String fs = "F:/Twinkle's Archive 2020/Java Course-2 Files/WEEK 4/BabyNames/us_babynames_small/testing/yob" + year + "short.csv";
        File f = new File(fs);
        FileResource fr = new FileResource(f);
        //FileResource fr = new FileResource();
        int totalBRH = 0;
        for(CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(0).equals(name) && gender.equals(rec.get(1)))
                break;
            if(gender.equals(rec.get(1)))
                totalBRH += Integer.parseInt(rec.get(2));
        }
        return totalBRH;
    }
}
