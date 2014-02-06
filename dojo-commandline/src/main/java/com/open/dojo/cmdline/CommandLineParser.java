package com.open.dojo.cmdline;


public class CommandLineParser {

	public MyOptions parse(String[] arguments) {
		MyOptions options = new MyOptions();
		for (int i = 0; i < arguments.length; ++i) {
			String argument = arguments[i];
			if (argument.equals("-r")) {
				++i;
				if (i >= arguments.length) {
					throw new IllegalArgumentException("expecting argument for -r");
				}
				try {
					options.maxRound = Integer.parseInt(arguments[i]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("expecting integer argument for -r");
				}
			} else if (argument.equals("-t")) {
				if (i+1 >= arguments.length) {
					throw new IllegalArgumentException("expecting argument for -t");
				}
				++i;
				try {
					options.duration = Integer.parseInt(arguments[i]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("expecting integer argument for -t");
				}
			} else if (argument.equals("-n")) {
				if (i+2 >= arguments.length) {
					throw new IllegalArgumentException("expecting argument for -n");
				}
				++i;
				try {
					options.width = Integer.parseInt(arguments[i++]);
					options.height = Integer.parseInt(arguments[i]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("expecting integer arguments for -n");
				}
			} else {
				options.others.add(arguments[i]);
			}
		}
		return options;
	}
	
	public void usage() {
		System.out.println("usage: myapplication [-t <duration>] [-r <max rounds>] [-n <width> <height>] <others arguments>");
	}

}
