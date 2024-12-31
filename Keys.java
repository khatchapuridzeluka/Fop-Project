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
	},
	
	PLUSPLUS {
		@Override
		public String toString() {
			return "++";
		}
	},
	
	MINUSMINUS {
		@Override
		public String toString() {
			return "--";
		}
	},
	
	ELSE {
		@Override
		public String toString() {
			return "else";
		}
	};
	
	// Returning the form in swift language
	public abstract String toString();
}
