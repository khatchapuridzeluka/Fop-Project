package interpreter;

// Any new Key word from Swift can be added here

public enum Keys {
	VAR {
		@Override
		public String toString() {
			return "var";
		}
	},
	PRINT {
		@Override
		public String toString() {
			return "print";
		}
	},
	IF {
		@Override
		public String toString() {
			return "if";
		}
	},
	
	WHILE {
		@Override
		public String toString() {
			return "while";
		}
	};
	
	
	
	public abstract String toString();
}
