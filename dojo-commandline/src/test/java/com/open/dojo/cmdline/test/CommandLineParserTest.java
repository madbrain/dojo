package com.open.dojo.cmdline.test;

import org.junit.Assert;
import org.junit.Test;

import com.open.dojo.cmdline.CommandLineParser;
import com.open.dojo.cmdline.MyOptions;

public class CommandLineParserTest {

	@Test
	public void testParseSimpleOptions() {
		CommandLineParser parser = new CommandLineParser();
		MyOptions options = parser.parse(new String[] { "-r", "100", "-t", "10", "my.map", "myplayer" });
		
		Assert.assertNotNull(options);
		Assert.assertEquals(10, options.duration);
		Assert.assertEquals(100, options.maxRound);
		Assert.assertEquals(2, options.others.size());
		Assert.assertEquals("my.map", options.others.get(0));
		Assert.assertEquals("myplayer", options.others.get(1));
	}
	
	@Test
	public void testNewMode() {
		CommandLineParser parser = new CommandLineParser();
		MyOptions options = parser.parse(new String[] { "-n", "10", "10", "my.map" });
		
		Assert.assertNotNull(options);
		Assert.assertEquals(10, options.width);
		Assert.assertEquals(10, options.height);
		Assert.assertEquals(1, options.others.size());
		Assert.assertEquals("my.map", options.others.get(0));
	}
	
	@Test
	public void testParseBadRoundOption() {
		CommandLineParser parser = new CommandLineParser();
		
		try {
			parser.parse(new String[] { "-r", "x" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting integer argument for -r", e.getMessage());
		}
	}
	
	@Test
	public void testParseMissingRoundArgument() {
		CommandLineParser parser = new CommandLineParser();
		
		try {
			parser.parse(new String[] { "-r" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting argument for -r", e.getMessage());
		}
	}
	
	@Test
	public void testParseBadTimeOption() {
		CommandLineParser parser = new CommandLineParser();
		
		try {
			parser.parse(new String[] { "-t", "x" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting integer argument for -t", e.getMessage());
		}
	}
	
	@Test
	public void testParseMissingTimeArgument() {
		CommandLineParser parser = new CommandLineParser();
		
		try {
			parser.parse(new String[] { "-t" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting argument for -t", e.getMessage());
		}
	}
	
	@Test
	public void testMissingNewArguments() {
		CommandLineParser parser = new CommandLineParser();
		try {
			parser.parse(new String[] { "-n", "1" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting argument for -n", e.getMessage());
		}
	}
	
	@Test
	public void testBadNewArguments() {
		CommandLineParser parser = new CommandLineParser();
		try {
			parser.parse(new String[] { "-n", "10", "x" });
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("expecting integer arguments for -n", e.getMessage());
		}
	}
	
}
