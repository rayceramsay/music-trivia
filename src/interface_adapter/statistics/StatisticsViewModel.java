package interface_adapter.statistics;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class StatisticsViewModel extends ViewModel {
    public static final String STATE_PROPERTY = "submitAnswerState";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final StatisticsState statisticsState = new StatisticsState();
    private final static String VIEW_NAME = "Statistics";
    public StatisticsViewModel() {
            super(VIEW_NAME);
    }
    public StatisticsState getState() {
        return statisticsState;
    }
    @Override
    public void firePropertyChanged() { support.firePropertyChange(STATE_PROPERTY, null, this.statisticsState);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
