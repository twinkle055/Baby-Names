
/**
 * Write a description of GrayScale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GrayScale {

    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels())
        {
            Pixel inP = inImage.getPixel(pixel.getX(), pixel.getY());
            int avg = (inP.getRed() + inP.getBlue() + inP.getGreen()) / 3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource i = new ImageResource(f);
            String fname = i.getFileName();
            i = makeGray(i);
            String newName = "grey-" + fname;
            i.setFileName(newName);
            i.draw();
            i.save();
        }
    }
}
