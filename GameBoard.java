package com.tag.mancalaSelf;

public class GameBoard {

	Pot[] pots = new Pot[14];
	int playerOneMancalaPot = Constants.playerOneMancalaIndex;
	int playerTwoMancalaIndex = Constants.playerTwoMancalaIndex;

	// initialize the game board
	void initGameBoard() {
		for (int i = 0; i < pots.length; i++) {
			pots[i] = new Pot();
			pots[i].setPositionIndex(i);
			if (i == (pots.length / 2) - 1 || i == (pots.length - 1)) {
				pots[i].setValue(0);
			} else {
				pots[i].setValue(4);
			}
		}
	}

	// user insert index and this will update grid according to input
	void updateGameBoard(int index) {
		int lastIndex = findLastIndex(index);

		if (isPotCapture(index, lastIndex)) {
			updatePotValue(index);
			Utility.print("Captured");
			capturePot(index, lastIndex);
		}
		updatePotValue(index);
		printGameBoard();

	}

	private boolean isPotCapture(int index, int lastIndex) {
		int perpendicularIndex = Math.abs(12 - lastIndex);

		if ((index < playerOneMancalaPot && lastIndex < playerOneMancalaPot)
				|| ((index > 7 && index < playerTwoMancalaIndex) && (lastIndex < playerTwoMancalaIndex))) {
			if (pots[lastIndex].getValue() == 0 && pots[perpendicularIndex].getValue() > 0) {
				return true;
			}
		}
		return false;
	}

	// if Pot captured then update pots according to capture rule
	void capturePot(int index, int lastIndex) {
		int perpendicularIndex = Math.abs(12 - lastIndex);

		if (lastIndex < playerOneMancalaPot) {
			int value = 1 + pots[perpendicularIndex].getValue() + pots[playerOneMancalaPot].getValue();
			pots[playerOneMancalaPot].setValue(value);
		}
		if (lastIndex > playerOneMancalaPot && lastIndex < playerTwoMancalaIndex) {
			int value = 1 + pots[perpendicularIndex].getValue() + pots[playerTwoMancalaIndex].getValue();
			pots[playerTwoMancalaIndex].setValue(value);
		}
		pots[index].setValue(0);
		pots[lastIndex].setValue(0);
		pots[perpendicularIndex].setValue(0);
	}

	// if Pot do not capture then simply update all Pot value
	void updatePotValue(int index) { // increment Pot value
		for (int j = pots[index].getPositionIndex() + 1; j <= pots[index].getPositionIndex()
				+ pots[index].getValue(); j++) {
			int value = pots[j].getValue();
			pots[j].setValue(value + 1);
			if (j == playerTwoMancalaIndex)
				break;
		}
		for (int j = 0; j < pots[index].getValue() - ((pots.length - 1) - pots[index].getPositionIndex()); j++) {
			int value = pots[j].getValue();
			pots[j].setValue(value + 1);
		}
		pots[index].setValue(0);
	}

	// find the last index according to the index value after update all Pot value
	int findLastIndex(int index) {
		return pots[index].getValue() + index;
	}

	// print the static game board
	void printGameBoard() {

		for (int j = pots.length - 2; j >= pots.length / 2; j--) {
			System.out.print("\t" + pots[j].getPositionIndex() + "(" + pots[j].getValue() + ")");
		}
		System.out.println();
		System.out.print("      -------------------------------------------------");
		System.out.println();
		System.out.print(
				pots[playerTwoMancalaIndex].getPositionIndex() + "(" + pots[playerTwoMancalaIndex].getValue() + ")");
		System.out.print("|                  PRATIK NAKUM                   |");
		System.out.println(
				pots[playerOneMancalaPot].getPositionIndex() + "(" + pots[playerOneMancalaPot].getValue() + ")");
		System.out.print("      -------------------------------------------------");
		System.out.println();
		for (int i = 0; i < (pots.length / 2) - 1; i++) {
			System.out.print("\t" + pots[i].getPositionIndex() + "(" + pots[i].getValue() + ")");
		}
		System.out.println();
	}

	boolean isPotEmpty(int index) {
		if (pots[index].getValue() == 0) {
			System.err.println("Please enter again Pot is EMPTY");
			return true;
		}
		return false;
	}

	boolean isLastIndexInMancalaPot(int index) {
		int lastIndex = findLastIndex(index);
		if (lastIndex == playerTwoMancalaIndex || lastIndex == playerOneMancalaPot) {
			return true;
		}
		return false;
	}

	public boolean isGameOver() {
		if (calculateSumOfIntArray(0, playerOneMancalaPot) == 0
				|| calculateSumOfIntArray(7, playerTwoMancalaIndex) == 0) {
			return true;
		}
		return false;
	}

	int calculateSumOfIntArray(int index1, int index2) {
		int sum = 0;
		for (int i = index1; i < index2; i++) {
			sum = sum + pots[i].getValue();
		}

		return sum;
	}

	int getFistPlayerScore() {
		return pots[playerOneMancalaPot].getValue() + calculateSumOfIntArray(0, playerOneMancalaPot);
	}

	int getSecondPlayerScore() {
		return pots[playerTwoMancalaIndex].getValue() + calculateSumOfIntArray(7, playerTwoMancalaIndex);
	}

}
