package com.open.dojo.cmdline;

import java.util.ArrayList;
import java.util.List;

import com.open.dojo.cmdline.annotations.Option;
import com.open.dojo.cmdline.annotations.OtherArguments;

public class MyOptions {

	public static final int DEFAULT_DURATION = 250;

	private static final int MAX_NUMBER_OF_ROUND = 1000;

	@Option(name="t", argName="duration", description="Durée d'un tour")
	public int duration = DEFAULT_DURATION;

	@Option(name="r", argName="max rounds", description="Nombre de tour")
	public int maxRound = MAX_NUMBER_OF_ROUND;

	@Option(name="n", index=0, argName="width", description="dimension de la map")
	public int width;

	@Option(name="n", index=1, argName="height")
	public int height;
	
	@OtherArguments
	public List<String> others = new ArrayList<String>();
}
