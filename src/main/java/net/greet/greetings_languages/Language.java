package net.greet.greetings_languages;

public enum Language {
	ENGLISH("Good day "),
	AFRIKAANS("Goie dag "),
	XHOSA("Mholweni emini nje "),
	ZULU("Usuku oluhle "),
	SPANISH("Buen dia "),
	JAPANESE("Yoi tsuitachi "),
	ARABIC("Yawm jayid "),
	HINDI("Achchha din "),
	FRENCH("Bonne journée "),
	RUSSIAN("Dobryy den ");
	
	private String greeting;
	
	 Language(String greeting) {
		
		this.greeting = greeting;
		
	}
	 
	 public String getGreeting() {
		 return greeting;
	 }
}













