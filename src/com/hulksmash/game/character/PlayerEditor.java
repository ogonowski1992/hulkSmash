package com.hulksmash.game.character;


import com.hulksmash.game.Constant;
import com.hulksmash.game.GameUIController;
import com.hulksmash.game.userinterface.UserCommandApiService;

public class PlayerEditor {
    private GameUIController gameUIController;

    public PlayerEditor(GameUIController gameUIController) {
        this.gameUIController = gameUIController;
    }

    public void create() {
        String name = "";
        while (name.equals("")) {
            name = UserCommandApiService.readWithPrompt("Enter your character name: ");
        }

        AttributePointAllocator pointToAllocate = new AttributePointAllocator(Constant.PLAYER_POINT_TO_ASSIGN_TO_ATTRIBUTES);
        UserCommandApiService.print(String.format("You have to assign points to attributes: %s", Attribute.getAllNames()));
        Integer strength = assignPointToAttribute(Attribute.STRENGTH, pointToAllocate);
        Integer resistance = assignPointToAttribute(Attribute.RESISTANCE, pointToAllocate);
        Integer luck = assignPointToAttribute(Attribute.LUCK, pointToAllocate);

        Player player = new Player(Constant.PLAYER_INITIAL_HEALTH, name, strength, resistance, luck, pointToAllocate.notAssignedPoints());
        gameUIController.goToGame(player);
    }

    private Integer assignPointToAttribute(Attribute attribute, AttributePointAllocator pointToAllocate) {
        UserCommandApiService.print("Point to allocate: " + pointToAllocate.notAssignedPoints());
        while (true) {
            Integer attributePoint = UserCommandApiService.readWithPromptForInt("How much points do you want add to: " + attribute.value());
            if (attributePoint == null) {
                UserCommandApiService.print("Wrong input. Try again.");
                continue;
            }
            if (pointToAllocate.tryAllocate(attributePoint)) {
                return attributePoint;
            } else {
                UserCommandApiService.print("You don't have enough points. Choose again.");
            }
        }
    }
}
