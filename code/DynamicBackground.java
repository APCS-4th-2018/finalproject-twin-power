/**
 * Class to faciliate changing background images.
 * Requires image files to be named from 0, 1, ..., n-1.
 *
 * @author (Sophia Du)
 * @version (190601)
 */
import javafx.scene.layout.*;
import javafx.scene.image.*;
public class DynamicBackground
{
    Background[] bg;
    
    /**
     * Creates a new DynamicBackground object.
     * 
     * @param   num         Number of backgrounds.
     * @param   width       Width of the background.
     * @param   height      Height of the background.
     * @param   filePath    Path of the file (up to/before actual file name).
     * @param   fileType    Type of the file (ex. .png or .jpg).
     */
    public DynamicBackground(int num, double width, double height, String filePath, String fileType)
    {
        bg = new Background[num];
        for(int i = 0; i < num; i++)
        {
            BackgroundImage img = new BackgroundImage(new Image(filePath + i + fileType),
                                  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                  BackgroundPosition.CENTER, new BackgroundSize(width,height,false,false,false,true));
            bg[i] = new Background(img);
        }
    }
    
    /**
     * Returns the Background at the given index.
     * 
     * @param   i   The index of the Background.
     * @return  The background.
     */
    public Background getBackground(int i)
    {
        return bg[i];
    }
}
