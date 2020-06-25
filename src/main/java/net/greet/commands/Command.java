package net.greet.commands;

import net.greet.processors.user_input.Context;

public interface Command {
	String execute(Context context);
}
