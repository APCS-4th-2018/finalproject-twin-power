/**
 * Extension of JavaFX's ProgressBar for color.
 *
 * @author (Sophia Du)
 * @version (190601)
 */
import javafx.scene.control.*;
public class ColoredProgressBar extends ProgressBar
{
    public ColoredProgressBar(String styleClass, double progress)
    {
        super(progress);
        getStyleClass().add(styleClass);
    }
}
