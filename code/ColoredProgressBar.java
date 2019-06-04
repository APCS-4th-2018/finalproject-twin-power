/**
 * Extension of JavaFX's ProgressBar for color.
 *
 * @author (Sophia Du)
 * @version (190601)
 */
import javafx.scene.control.*;
public class ColoredProgressBar extends ProgressBar
{
    /**
     * Creates a ColoredProgressBar with the specified style class
     * and progress.
     * 
     * @param   styleClass  name of the style class
     * @param   progress    Progress of the bar (0 to 1).
     */
    public ColoredProgressBar(String styleClass, double progress)
    {
        super(progress);
        getStyleClass().add(styleClass);
    }
}
