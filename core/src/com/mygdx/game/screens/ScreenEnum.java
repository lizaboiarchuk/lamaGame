package com.mygdx.game.screens;

public enum ScreenEnum {

    AUTHORIZATION_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new AuthorizationScreen();
        }
    },
    REGISTRATION_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new RegistrationScreen();
        }
    },
    MENU_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new MenuScreen();
        }
    },
    CHOICE_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new ChoiceScreen();
        }
    },
    GAME_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new GameScreen();
        }
    },
    GAME_OVER_SCREEN {
        public AbstractScreen getScreen(Object... params) {
            return new GameOverScreen();
        }
    };

    public abstract AbstractScreen getScreen(Object... params);
}