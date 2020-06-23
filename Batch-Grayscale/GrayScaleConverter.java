
/**
 * Write a description of GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class GrayScaleConverter {
    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels())
        {
            Pixel inP = inImage.getPixel(pixel.getX(), pixel.getY());
            int avg = (inP.getRed() + inP.getGreen() + inP.getBlue()) / 3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    
    public void printGray(){
        ImageResource ir = new ImageResource();
        ImageResource grayImage = makeGray(ir);
        grayImage.draw();
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource();
            ImageResource gray = makeGray(ir);
            gray.draw();
        }
    }
    
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            String fName = ir.getFileName();
            String newName = "gray-" + fName;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }
}