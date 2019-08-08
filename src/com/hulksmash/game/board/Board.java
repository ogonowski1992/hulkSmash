package com.hulksmash.game.board;

import com.hulksmash.game.userinterface.ColorsForConsole;
import com.hulksmash.game.character.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board implements Serializable {
    private BoardCell[][] board;
    private int height;
    private int width;
    private Position playerPosition;
    private List<Position> rockPositions;
    private int borderSize = 2;
    private final double rockAmountPercent = 0.07;
    private final int noFogRadius = 3;

    public Board(int w, int h) {
        this.width = w + borderSize;
        this.height = h + borderSize;
        initRockPositions();
        this.board = new BoardCell[this.width][this.height];
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                board[x][y] = new BoardCell();
            }
        }
        clareBoardForNextFrame();

    }

    private void initRockPositions() {
        rockPositions = new ArrayList<>();
        int rockAmount = (int) (rockAmountPercent * (width - borderSize) * (height - borderSize));
        for (int i = 0; i < rockAmount; i++) {
            int x = ThreadLocalRandom.current().nextInt(1, width - borderSize + 1);
            int minY = 1;
            boolean isRockToCloseToPlayerInOneDimension = x < 5;
            if (isRockToCloseToPlayerInOneDimension) {
                minY = 5;
            }
            int y = ThreadLocalRandom.current().nextInt(minY, height - borderSize + 1);
            rockPositions.add(new Position(x, y));
        }

    }

    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                stringBuilder.append(board[x][y].getAppearance());
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    public void setPlayer(Position position, String code) {
        playerPosition = position;
        board[playerPosition.getX()][playerPosition.getY()].setBoardElement(BoardElement.PLAYER);
        board[playerPosition.getX()][playerPosition.getY()].setAppearance(code);
        clareFogOfWar();
    }

   public void setEnemy(Position position, String code) {
        board[position.getX()][position.getY()].setBoardElement(BoardElement.ENEMY);
        board[position.getX()][position.getY()].setAppearance(code);

    }

    private void clareFogOfWar() {
        int startX = Math.max(playerPosition.getX() - noFogRadius, 1);
        int startY = Math.max(playerPosition.getY() - noFogRadius, 1);
        int endX = Math.min(playerPosition.getX() + noFogRadius, width - 1);
        int endY = Math.min(playerPosition.getY() + noFogRadius, height - 1);
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                board[x][y].unFog();
            }
        }
    }

    public void clareBoardForNextFrame() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1) {
                    board[x][y].setBoardElement(BoardElement.BORDER);
                    board[x][y].setAppearance("|");
                } else if (y == 0 || y == height - 1) {
                    board[x][y].setBoardElement(BoardElement.BORDER);
                    board[x][y].setAppearance("-");
                } else {
                    board[x][y].setBoardElement(BoardElement.SPACE);
                    board[x][y].setAppearance(" ");
                }
            }
        }
        for (Position p : rockPositions) {
            board[p.getX()][p.getY()].setBoardElement(BoardElement.OBSTACLE);
            board[p.getX()][p.getY()].setAppearance(ColorsForConsole.ANSI_WHITE_BACKGROUND + " " + ColorsForConsole.ANSI_RESET);
        }
    }

   public boolean isPositionAllowedForPlayer(Position position) {
        return board[position.getX()][position.getY()].isAvailableForPlayer();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
