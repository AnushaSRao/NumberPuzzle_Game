import java.awt.*;
import java.util.Arrays;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}

	public int getHeight() {
		return 250;
	}

	public int getXPosition() {
		return 200;
	}

	public int getYPosition() {
		return 200;
	}

	public String getTitle() {
		return "Number Puzzle";
	}

	public int getShuffleButtonFontSize() {
		return 12;
	}

	public int getNumbersFontSize() {
		return 12;
	}

	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}

	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game) {
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();

		// Your logic here
		int flag =0;
		for(int i=0;i<16;i++) {
			if(buttons[i].equals(buttonClicked)) {
				if(i%4==0) {
					if(emptyCellId ==(i+1) || emptyCellId ==(i+4) || emptyCellId ==(i-4)) 
						flag =1;
				}
				else if(i%4 ==1 || i%4==2) {
					if(emptyCellId ==(i+1) || emptyCellId ==(i-1) || emptyCellId ==(i+4) || emptyCellId ==(i-4)) 
						flag =1;
				}
				else {
					if(emptyCellId ==(i-1) || emptyCellId ==(i+4) || emptyCellId ==(i-4)) 
						flag =1;
				}
			}
		}
		if(flag==0)
			return emptyCellId;
		
		swapButton(buttons[emptyCellId], buttonClicked);
		for(int i=0;i<16;i++) {
			if(buttons[i].equals(buttonClicked)) {
				emptyCellId = i;
				break;
			}
		}
		game.setEmptyCellId(emptyCellId);
		return emptyCellId;

	}

	public int[] getRandomNumbersForGrid() {
		int arr[] = new int[15];

		// Your logic here
		for(int i=0;i<15;i++) {
			int a = getRandomNumber();
			int num = (a % 15) + 1;
			while (linearSearch(arr,num))
				num = (getRandomNumber() % 15) + 1;
			arr[i] = num;
		}
		return arr;
	}

	private boolean linearSearch(int[] arr, int num) {
		// TODO Auto-generated method stub
		for(int i:arr)
			if(i==num)
				return true;
		return false;
	}

	public boolean checkForWinner(Button[] buttons) {
		boolean winner = true;

		// Your Logic here
		int[] finalArray = getIntegerArrayOfButtonIds(buttons);
		for(int i=0;i<15;i++) {
			if(finalArray[i]!=(i+1)) {
				winner = false;
				break;
			}
		}
				
		return winner;
	}
}