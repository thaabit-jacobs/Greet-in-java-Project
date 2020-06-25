package net.greet.commands;

import net.greet.processors.user_input.Context;

public class LanguageCommand implements Command{
	
	public String execute(Context context) {
		return "\nlanguages \n========= \nENGLISH \nAFRIKAANS \nXHOSA \nZULU \nSPANISH \nJAPANESE \nARABIC \nHINDI \nFRENCH \nRUSSIAN";
	}
}
