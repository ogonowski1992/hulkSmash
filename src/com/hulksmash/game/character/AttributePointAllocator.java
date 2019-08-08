package com.hulksmash.game.character;

 class AttributePointAllocator {
    private int pointsToAllocate;

     AttributePointAllocator(int pointsToAllocate) {
        this.pointsToAllocate = pointsToAllocate;
    }

     boolean tryAllocate(Integer attributePoint) {
        if(attributePoint <= pointsToAllocate) {
            pointsToAllocate -= attributePoint;
            return true;
        }
        return false;
    }

     Integer notAssignedPoints() {
        return pointsToAllocate;
    }


 }
