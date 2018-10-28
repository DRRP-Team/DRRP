
class ConversationController play {

	static int GetArgument( int lineID ) {
	    LineIdIterator it = LineIdIterator.Create( lineID );
	    int itLineID      = it.Next();

		if ( itLineID > 0 ) {
			Line convLine = level.Lines[ itLineID ];

			return convLine.args[ 4 ];
		} else {
			return -1;
        }
	}

	static bool SetArgument( int lineID, int lineArg ) {
	    LineIdIterator it = LineIdIterator.Create( lineID );
	    int itLineID = it.Next();

		if ( itLineID > 0 ) {
			Line convLine = level.Lines[ itLineID ];
			convLine.args[ 4 ] = lineArg;

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
} // of class ConversationController play {


class ShaderControllerActor : EventHandler {
    static int SetEnabled(int player, String name, bool enabled) {
        if(player != 0) {
            Shader.SetEnabled(players[player - 1], name, enabled);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetEnabled(players[i], name, enabled);
            }
        }

        return 1;
    }

    static int SetUniformFloat(int player, String name, String uniform, float value) {
        if(player != 0) {
            Shader.SetUniform1f(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1f(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }

    static int SetUniformInt(int player, String name, String uniform, int value) {
        if(player != 0) {
            Shader.SetUniform1i(players[player - 1], name, uniform, value);
        } else {
            for(int i = 0; i < players.Size(); i++) {
                Shader.SetUniform1i(players[player - 1], name, uniform, value);
            }
        }

        return 1;
    }
}


