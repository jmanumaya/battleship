package game;

public class Battleship {
	private char[][] tablePlayer = new char[10][10];

	public boolean checksPosition(int posI, int posJ) {
		boolean valid = false;

		// If the position is between the limits and there isn't a ship on the position,
		// the position is valid
		if ((posI >= 0 && posI < tablePlayer.length) && (posJ >= 0 && posJ < tablePlayer[posJ].length)
				&& tablePlayer[posI][posJ] != 'S') {
			valid = true;
		}

		// We check the adjacent positions

		// Check if the adjacent position is between the limits -> i, j - 1
		if ((posI >= 0 && posI < tablePlayer.length) && (posJ - 1 >= 0 && posJ - 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI][posJ - 1] == 'S') {
				valid = false;
			}
		}

		// Check if the adjacent position is between the limits -> i, j + 1
		else if ((posI >= 0 && posI < tablePlayer.length) && (posJ + 1 >= 0 && posJ + 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI][posJ + 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j
		else if ((posI - 1 >= 0 && posI - 1 < tablePlayer.length) && (posJ >= 0 && posJ < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI - 1][posJ] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j - 1
		else if ((posI - 1 >= 0 && posI - 1 < tablePlayer.length)
				&& (posJ - 1 >= 0 && posJ - 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI - 1][posJ - 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i - 1, j + 1
		else if ((posI - 1 >= 0 && posI - 1 < tablePlayer.length)
				&& (posJ + 1 >= 0 && posJ + 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI - 1][posJ + 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j
		else if ((posI + 1 >= 0 && posI + 1 < tablePlayer.length) && (posJ >= 0 && posJ < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI + 1][posJ] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j - 1
		else if ((posI + 1 >= 0 && posI + 1 < tablePlayer.length)
				&& (posJ - 1 >= 0 && posJ - 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI + 1][posJ - 1] == 'S') {
				valid = false;
			}
		}
		// Check if the adjacent position is between the limits -> i + 1, j + 1
		else if ((posI + 1 >= 0 && posI + 1 < tablePlayer.length)
				&& (posJ + 1 >= 0 && posJ + 1 < tablePlayer[posJ].length)) {
			// Check if there is a ship in the position
			if (tablePlayer[posI + 1][posJ + 1] == 'S') {
				valid = false;
			}
		}

		// Return boolean true or false
		return valid;
		
			/* boolean valid = true;
	        
	        // Check if position is within bounds
	        if (posI >= 0 && posI < tablePlayer.length && 
	            posJ >= 0 && posJ < tablePlayer[0].length) {
	            
	            // Check current position
	            if (tablePlayer[posI][posJ] == 'S') {
	                valid = false;
	            }
	            
	            // Check adjacent positions
	            for (int i = Math.max(0, posI - 1); i <= Math.min(tablePlayer.length - 1, posI + 1) && valid; 
	                 i++) {
	                for (int j = Math.max(0, posJ - 1); 
	                     j <= Math.min(tablePlayer[0].length - 1, posJ + 1) && valid; 
	                     j++) {
	                    if (tablePlayer[i][j] == 'S') {
	                        valid = false;
	                    }
	                }
	            }
	        } else {
	            valid = false;
	        }
	        
	        return valid; */
	}
}
