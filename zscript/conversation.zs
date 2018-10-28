/**
 *Copyright (c) 2018 DRRP-Team
 *
 *This software is released under the MIT License.
 *https://opensource.org/licenses/MIT
 */

class ConversationController play {

    static int GetArgument(int lineID) {
        LineIdIterator it = LineIdIterator.Create(lineID);
        int itLineID      = it.Next();

        if (itLineID > 0) {
            Line convLine = level.Lines[itLineID];

            return convLine.args[4];
        } else {
            return -1;
        }
    }

    static bool SetArgument(int lineID, int lineArg) {
        LineIdIterator it = LineIdIterator.Create(lineID);
        int itLineID = it.Next();

        if (itLineID > 0) {
            Line convLine = level.Lines[itLineID];
            convLine.args[4] = lineArg;

            return true;
        }

        return false;
    }

    static int GetActorArgument(int actorID) {
        ActorIterator it = ActorIterator.Create(actorID);
        Actor actor      = it.Next();

        if (actor) {
            return actor.args[4];
        } else {
            return -1;
        }
    }

    static bool SetActorArgument(int actorID, int lineArg) {
        ActorIterator it = ActorIterator.Create(actorID);
        Actor actor      = it.Next();

        if (actor) {
            actor.args[4] = lineArg;
            return true;
        }

        return false;
    }
}