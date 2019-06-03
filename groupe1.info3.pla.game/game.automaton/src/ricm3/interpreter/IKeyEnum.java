package ricm3.interpreter;

public class IKeyEnum {
	enum Special{
		SPACE, ENTER, FU, FD, FL, FR, NULL
	}
	enum Kind{
		Simple, Special
	}
	public char simple;
	public Special special;
	Kind kind;
	
	public IKeyEnum() {
		kind = Kind.Simple;
		simple = '\0';
	}
		
	public IKeyEnum(String string) {
		if(string.length() == 1) {
			simple = string.charAt(0);
			kind = Kind.Simple;
		} else {
			switch (string) {
			case "SPACE": special = Special.SPACE; break;
			case "ENTER": special = Special.ENTER; break;
			case "FU": special = Special.FU; break;
			case "FD": special = Special.FD; break;
			case "FL": special = Special.FL; break;
			case "FR": special = Special.FR; break;

			default:
				break;
			}
			kind = Kind.Special;
		}
	}

	public boolean isSimple() {return kind == Kind.Simple;}
	public boolean isSpecial() {return kind == Kind.Special;}

	public boolean equals(char c) {
		return simple == c;
	}
	
	public boolean equals(Special c) {
		return special == c;
	}
	
	public boolean equals(IKeyEnum c) {
		return (kind == Kind.Simple && simple == c.simple) || (kind == Kind.Special && special == c.special);
	}
	
}
