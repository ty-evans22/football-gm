package core.common;

public enum Position {
	QUARTERBACK,
	RUNNING_BACK,
	WIDE_RECEIVER,
	TIGHT_END,
	OFFENSIVE_LINEMAN,
	DEFENSIVE_BACK,
	LINEBACKER,
	DEFENSIVE_LINEMAN,
	KICKER,
	PUNTER;
	
	@Override
	public String toString() {
		switch(this) {
			case QUARTERBACK:
				return "Quarterback";
			case RUNNING_BACK:
				return "Running Back";
			case WIDE_RECEIVER:
				return "Wide Receiver";
			case TIGHT_END:
				return "Tight End";
			case OFFENSIVE_LINEMAN:
				return "Offensive Lineman";
			case DEFENSIVE_BACK:
				return "Defensive Back";
			case LINEBACKER:
				return "Linebacker";
			case DEFENSIVE_LINEMAN:
				return "Defensive Lineman";
			case KICKER:
				return "Kicker";
			case PUNTER:
				return "Punter";
		}
		return null;
	}
	
	/**
	 * Gets the shorthand version of the position.
	 * @return
	 * 		the shorthand representation of the position
	 */
	public String toStringShorthand() {
		switch(this) {
			case QUARTERBACK:
				return "QB";
			case RUNNING_BACK:
				return "RB";
			case WIDE_RECEIVER:
				return "WR";
			case TIGHT_END:
				return "TE";
			case OFFENSIVE_LINEMAN:
				return "OL";
			case DEFENSIVE_BACK:
				return "DB";
			case LINEBACKER:
				return "LB";
			case DEFENSIVE_LINEMAN:
				return "DL";
			case KICKER:
				return "K";
			case PUNTER:
				return "P";
		}
		return null;
	}
}
