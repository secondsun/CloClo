package net.saga.game.cloclo.characters;

public class AnimationMetadata {

    public final int spritesInMovement, offsetRow, frameTime;

    public AnimationMetadata(int spritesInMovement, int offsetRow, int frameTime) {
        this.spritesInMovement = spritesInMovement;
        this.offsetRow = offsetRow;
        this.frameTime = frameTime;
    }


    public static class Builder {


        private int spritesInMovement;
        private int offsetRow;
        private int frameTime;

        /**
         * The number of sprites in an animation cycle.
         *
         * In a Sprite sheet this will start at the offsetRow and incriment by 1
         * at each point in the frameTime
         *
         * @param spritesInAnimationCycle number of Sprites
         * @return the builder
         */
        public Builder spritesInMovement(int spritesInAnimationCycle) {
            this.spritesInMovement = spritesInAnimationCycle;
            return this;
        }

        /**
         * The number of sprites in an animation cycle.
         *
         * In a Sprite sheet this will start at the offsetRow and incriment by 1
         * at each point in the frameTime
         *
         * @param offsetRow the row the animation "begins" on.
         * @return the builder
         */
        public Builder offsetRow(int offsetRow) {
            this.offsetRow = offsetRow;
            return this;
        }

        /**
         * The number of sprites in an animation cycle.
         *
         * In a Sprite sheet this will start at the offsetRow and incriment by 1
         * at each point in the frameTime
         *
         * @param frameTime number of frames and animation lasts
         * @return the builder
         */
        public Builder frameTime(int frameTime) {
            this.frameTime = frameTime;
            return this;
        }

        public AnimationMetadata build() {
            return new AnimationMetadata(spritesInMovement,  offsetRow,  frameTime);
        }

    }
}
