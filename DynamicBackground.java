/**
 * Write a description of class DynamicBackground here.
 *
 * @author (Sophia Du)
 * @version (190601)
 */
import javafx.scene.layout.*;
import javafx.scene.image.*;
public class DynamicBackground
{
    Background[] bg;
    
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
    public Background getBackground(int i)
    {
        return bg[i];
    }
}
