package view;

import data_access.game_data.GameDataAccessInterface;
import entity.RoundFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.game_settings.GameSettingsViewModel;
import interface_adapter.round.RoundViewModel;
import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInteractor;
import use_case.create_game.CreateGameOutputBoundary;

public class GameSettingsViewFactory {

    private GameSettingsViewFactory() {}

    public static GameSettingsView create(ViewManagerModel viewManagerModel,
                                           RoundViewModel roundViewModel,
                                           CreateGameViewModel createGameViewModel,
                                           GameSettingsViewModel gameSettingsViewModel,
                                           GameDataAccessInterface createGameDataAccessInterface,
                                           RoundFactory roundFactory) {

        CreateGameController createGameController = createGameUseCase(viewManagerModel, roundViewModel, createGameViewModel, createGameDataAccessInterface, roundFactory);

        return new GameSettingsView(gameSettingsViewModel, viewManagerModel, createGameController);
    }

    private static CreateGameController createGameUseCase(ViewManagerModel viewManagerModel,
                                                              RoundViewModel roundViewModel,
                                                              CreateGameViewModel createGameViewModel,
                                                              GameDataAccessInterface gameDataAccessObject,
                                                              RoundFactory roundFactory) {
        CreateGameOutputBoundary createGamePresenter = new CreateGamePresenter(viewManagerModel, roundViewModel, createGameViewModel);
        CreateGameInputBoundary createGameInteractor = new CreateGameInteractor(gameDataAccessObject, createGamePresenter, roundFactory);

        return new CreateGameController(createGameInteractor);
    }
}
