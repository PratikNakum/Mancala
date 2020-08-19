package com.tag.mancalaSelf;

public class GameManager {
	GameBoard gameBoard = new GameBoard();
	Player[] player = new Player[2];

	void startGame() {
		gameBoard.initGameBoard();
		gameBoard.printGameBoard();
		for (int i = 0; i < player.length; i++) {
			player[i] = new Player();
			setPlayerInfo(player[i]);
		}

		Player currentPlayer = player[0];
		while (true) {
			int index = Utility.getInt(currentPlayer.getName() + " please enter Index: ");
			if (checkValidInput(index, currentPlayer)) {
				if (!gameBoard.isPotEmpty(index)) {
					if (gameBoard.isLastIndexInMancalaPot(index)) {
						System.out.println("In Pot");
						currentPlayer = changePlayer(currentPlayer);
					}
					gameBoard.updateGameBoard(index);
					currentPlayer = changePlayer(currentPlayer);

				}
				// If game over than
				if (gameBoard.isGameOver()) {
					declarWinner();
					break;
				}
			}
		}
	}

	boolean checkValidInput(int index, Player currentPlayer) {
		if (currentPlayer == player[0] && isFirstPlayerInputValid(index)
				|| (currentPlayer == player[1] && isSecondPlayerInputValid(index)))
			return true;
		else
			System.err.println("Enter valid index");
		return false;
	}

	void declarWinner() {
		int playerOnePotScore = gameBoard.getFistPlayerScore();
		int playerTwoPotScore = gameBoard.getSecondPlayerScore();
		if (playerOnePotScore > playerTwoPotScore) {
			System.out.println(player[0].getName() + " is Winner");
		} else {
			System.out.println(player[1].getName() + " is Winner");
		}
		System.out.println(playerOnePotScore + "-" + playerTwoPotScore);
	}

	boolean isFirstPlayerInputValid(int input) {
		if (input >= 0 && input < Constants.playerOneMancalaIndex)
			return true;
		return false;
	}

	boolean isSecondPlayerInputValid(int input) {
		if (input < Constants.playerTwoMancalaIndex && input > Constants.playerOneMancalaIndex)
			return true;
		return false;
	}

	void setPlayerInfo(Player player) {
		player.setName(Utility.getString("Enter player name: "));
	}

	Player changePlayer(Player currentPlayer) {
		if (currentPlayer.equals(player[0]))
			return player[1];
		return player[0];
	}

}