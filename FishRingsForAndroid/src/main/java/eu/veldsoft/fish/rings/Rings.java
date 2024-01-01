package eu.veldsoft.fish.rings;

public class Rings {
	private int rings[][] = new int[3][12];

	private int coordiantes[][][] = new int[3][12][2];

	private int width;

	private int height;

	private int pdiam;

	private int rdiam;

	private int moves;

	Rings() {
		init(0, 0);
	}

	Rings(int width, int height) {
		init(width, height);
	}

	void init(int width, int height) {
		this.width = width;
		this.height = height;

		pdiam = (int) (Math.min(width, height) * 0.1);
		rdiam = (int) (2.5 * pdiam);

		for (double i = 0, alpha = +3.5; i < 12; i++, alpha++) {
			coordiantes[0][(int) i][0] = (int) (Math.min(width, height) / 2.0
					+ rdiam * Math.cos(alpha * 2 * Math.PI / 12));
			coordiantes[0][(int) i][1] = (int) (Math.min(width, height) / 2.0 - Math.sqrt(2.0) * rdiam / 2.0
					+ rdiam * Math.sin(alpha * 2 * Math.PI / 12));
		}

		for (double i = 0, alpha = -3.5; i < 12; i++, alpha--) {
			coordiantes[1][(int) i][0] = (int) (Math.min(width, height) / 2.0 + Math.sqrt(2.0) * rdiam / 2.0
					+ rdiam * Math.cos(alpha * 2 * Math.PI / 12));
			coordiantes[1][(int) i][1] = (int) (Math.min(width, height) / 2.0
					+ rdiam * Math.sin(alpha * 2 * Math.PI / 12));
		}

		for (double i = 0, alpha = -2.5; i < 12; i++, alpha++) {
			coordiantes[2][(int) i][0] = (int) (Math.min(width, height) / 2.0 - Math.sqrt(2.0) * rdiam / 2.0
					+ rdiam * Math.cos(alpha * 2 * Math.PI / 12));
			coordiantes[2][(int) i][1] = (int) (Math.min(width, height) / 2.0
					+ rdiam * Math.sin(alpha * 2 * Math.PI / 12));
		}

		rings[0][0] = 4;
		for (int i = 1; i < 11; i++)
			rings[0][i] = 1;
		rings[0][11] = 4;

		for (int i = 0; i < 5; i++) {
			rings[1][i] = 4;
			rings[2][i] = 4;
		}
		for (int i = 5; i < 11; i++) {
			rings[1][i] = 2;
			rings[2][i] = 3;
		}
		rings[1][11] = 1;
		rings[2][11] = 1;

		moves = 0;
	}

	void cwa() {
		int temp = rings[0][0];
		for (int i = 0; i < 11; i++)
			rings[0][i] = rings[0][i + 1];
		rings[0][11] = temp;

		rings[1][3] = rings[0][0];
		rings[1][11] = rings[0][8];
		rings[2][11] = rings[0][3];
		rings[2][3] = rings[0][11];
	}

	void ccwa() {
		int temp = rings[0][11];
		for (int i = 11; i > 0; i--)
			rings[0][i] = rings[0][i - 1];
		rings[0][0] = temp;

		rings[1][3] = rings[0][0];
		rings[1][11] = rings[0][8];
		rings[2][11] = rings[0][3];
		rings[2][3] = rings[0][11];
	}

	void cwb() {
		int temp = rings[1][0];
		for (int i = 0; i < 11; i++)
			rings[1][i] = rings[1][i + 1];
		rings[1][11] = temp;

		rings[0][0] = rings[1][3];
		rings[0][8] = rings[1][11];
		rings[2][1] = rings[1][1];
		rings[2][4] = rings[1][4];
	}

	void ccwb() {
		int temp = rings[1][11];
		for (int i = 11; i > 0; i--)
			rings[1][i] = rings[1][i - 1];
		rings[1][0] = temp;

		rings[0][0] = rings[1][3];
		rings[0][8] = rings[1][11];
		rings[2][1] = rings[1][1];
		rings[2][4] = rings[1][4];
	}

	void cwc() {
		int temp = rings[2][0];
		for (int i = 0; i < 11; i++)
			rings[2][i] = rings[2][i + 1];
		rings[2][11] = temp;

		rings[0][3] = rings[2][11];
		rings[0][11] = rings[2][3];
		rings[1][1] = rings[2][1];
		rings[1][4] = rings[2][4];
	}

	void ccwc() {
		int temp = rings[2][11];
		for (int i = 11; i > 0; i--)
			rings[2][i] = rings[2][i - 1];
		rings[2][0] = temp;

		rings[0][3] = rings[2][11];
		rings[0][11] = rings[2][3];
		rings[1][1] = rings[2][1];
		rings[1][4] = rings[2][4];
	}

	void shuffle() {
		for (int i = 0; i < 10 * rings.length * rings[0].length; i++)
			switch (Util.PRNG.nextInt(6)) {
			case 0:
				cwa();
				break;
			case 1:
				ccwa();
				break;
			case 2:
				cwb();
				break;
			case 3:
				ccwb();
				break;
			case 4:
				cwc();
				break;
			case 5:
				ccwc();
				break;
			}

		moves = 0;
	}

	void reset() {
		init(width, height);
	}

	boolean eval(String sequ) {
		for (int i = -1, rep = 0; i < sequ.length() - 1;)
			if (sequ.charAt(++i) == '+') {
				if ('0' <= sequ.charAt(++i) && sequ.charAt(i) <= '9')
					rep = sequ.charAt(i++) - '0';
				else
					rep = 1;

				if (sequ.charAt(i) == 'A')
					for (int j = 0; j < rep; j++)
						cwa();
				else if (sequ.charAt(i) == 'B')
					for (int j = 0; j < rep; j++)
						cwb();
				else if (sequ.charAt(i) == 'C')
					for (int j = 0; j < rep; j++)
						cwc();
				else
					return false;
			} else if (sequ.charAt(++i) == '-') {
				if ('0' <= sequ.charAt(++i) && sequ.charAt(i) <= '9')
					rep = sequ.charAt(i++) - '0';
				else
					rep = 1;

				if (sequ.charAt(i) == 'A')
					for (int j = 0; j < rep; j++)
						ccwa();
				else if (sequ.charAt(i) == 'B')
					for (int j = 0; j < rep; j++)
						ccwb();
				else if (sequ.charAt(i) == 'C')
					for (int j = 0; j < rep; j++)
						ccwc();
				else
					return false;
			} else
				return false;

		return true;
	}

	boolean action(int x, int y) {
		boolean done = false;

		for (int j = 0; j < 18; j++)
			switch (j) {
			case 1:
			case 2:
			case 4:
			case 5:
				if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y - coordiantes[0][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					cwa();
					done = true;
				}
				break;
			case 6:
			case 7:
			case 9:
			case 10:
				if ((Math.pow(x - coordiantes[0][j][0], 2.0) + Math.pow(y - coordiantes[0][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					ccwa();
					done = true;
				}
				break;
			}

		for (int j = 0; j < 18; j++)
			switch (j) {
			case 0:
			case 2:
			case 5:
				if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y - coordiantes[1][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					ccwb();
					done = true;
				}
				break;
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				if ((Math.pow(x - coordiantes[1][j][0], 2.0) + Math.pow(y - coordiantes[1][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					cwb();
					done = true;
				}
				break;
			}

		for (int j = 0; j < 18; j++)
			switch (j) {
			case 0:
			case 2:
			case 5:
				if ((Math.pow(x - coordiantes[2][j][0], 2.0) + Math.pow(y - coordiantes[2][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					ccwc();
					done = true;
				}
				break;
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				if ((Math.pow(x - coordiantes[2][j][0], 2.0) + Math.pow(y - coordiantes[2][j][1], 2.0)) < Math
						.pow(pdiam / 2.0, 2.0)) {
					cwc();
					done = true;
				}
				break;
			}

		moves++;

		return (done);
	}

	boolean isDone() {
		for (int i = 0; i < 11; i++)
			if (rings[0][i] != rings[0][i + 1] && i != 0 && i != 10)
				return false;
			else if (rings[1][i] != rings[1][i + 1] && i != 4 && i != 10)
				return false;
			else if (rings[2][i] != rings[2][i + 1] && i != 4 && i != 10)
				return false;

		return true;
	}

	int[] getState() {
		int[] result = new int[rings[0].length + rings[1].length + rings[2].length];
		for (int a = 0, j = 0; a < rings.length; a++) {
			for (int i = 0; i < rings[a].length; i++, j++) {
				result[j] = rings[a][i];
			}
		}
		return result;
	}
}
