package entity;

import java.util.List;

/**
 * Extension of Round for multiple choice version of gameplay
 */
public interface OptionRound extends Round {
    /**
     * @return list of options
     */
    List<String> getOptions();
}
