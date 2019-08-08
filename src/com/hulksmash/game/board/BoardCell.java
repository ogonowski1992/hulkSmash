package com.hulksmash.game.board;

import com.hulksmash.game.userinterface.ColorsForConsole;

import java.io.Serializable;

class BoardCell implements Serializable {

    BoardCell() {
        inFogOfWar = true;
    }

    private BoardElement boardElement;
    private String appearance;
    private boolean inFogOfWar;

    void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    void setBoardElement(BoardElement boardElement) {
        if (boardElement == BoardElement.BORDER) {
            inFogOfWar = false;
        }
        this.boardElement = boardElement;
    }

    void unFog() {
        inFogOfWar = false;
    }

    String getAppearance() {
        if (inFogOfWar) {
            return ColorsForConsole.ANSI_BLUE_BACKGROUND + " " + ColorsForConsole.ANSI_RESET;
        }
        return appearance;
    }

    boolean isAvailableForPlayer() {
        return boardElement == BoardElement.SPACE;
    }
}
