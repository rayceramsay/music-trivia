package entity;

import java.util.List;

/**
 * An extension of round where users can select their answer from predetermined options
 */
public interface OptionRound extends Round {
    List<String> getOptions();
}
