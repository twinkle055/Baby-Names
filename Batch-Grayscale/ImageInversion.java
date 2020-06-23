
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class ImageInversion {
    
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels())
        {
            Pixel inP = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inP.getRed());
            pixel.setGreen(255 - inP.getGreen());
            pixel.setBlue(255 - inP.getBlue());
        }
        return outImage;
    }
    
    public void printInverted(){
        ImageResource ir = new ImageResource();
        ImageResource invertedImage = makeInversion(ir);
        invertedImage.draw();
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource();
            ImageResource inverted = makeInversion(ir);
            inverted.draw();
        }
    }
    
    public void doSave(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource ir = new ImageResource(f);
            ImageResource inverted = new ImageResource(ir);
            inverted = makeInversion(ir);
            String fName = ir.getFileName();
            String newName = "inverted-" + fName;
            inverted.setFileName(newName);
            inverted.draw();
            inverted.save();
        }
    }
}
