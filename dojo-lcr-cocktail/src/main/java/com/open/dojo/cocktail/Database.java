package com.open.dojo.cocktail;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
	
	private static final File dataRoot = new File("database");
	
	private static int ingredientId = 1;
	private static int cocktailId = 1;
	
	static {
		File ingredientsDir = new File(dataRoot, "ingredients");
		if (ingredientsDir.exists()) {
			for (File file : ingredientsDir.listFiles()) {
				ingredientId = Math.max(ingredientId, Integer.parseInt(file.getName()));
			}
		}
		++ingredientId;
		File cocktailsDir = new File(dataRoot, "cocktails");
		if (cocktailsDir.exists()) {
			for (File file : cocktailsDir.listFiles()) {
				cocktailId = Math.max(cocktailId, Integer.parseInt(file.getName()));
			}
		}
		++cocktailId;
	}

	public static List<Ingredient> getAllIngredients() {
		File ingredientsDir = new File(dataRoot, "ingredients");
		List<Ingredient> result = new ArrayList<Ingredient>();
		try {
			if (ingredientsDir.exists()) {
				for (File file : ingredientsDir.listFiles()) {
					result.add(readIngredient(file));
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return result;
	}

	public static void saveOrUpdateIngredient(Ingredient ingredient) {
		if (ingredient.getId() == 0) {
			ingredient.setId(ingredientId++);
		}
		File ingredientFile = new File(dataRoot, "ingredients/" + String.valueOf(ingredient.getId()));
		ingredientFile.getParentFile().mkdirs();
		try {
			writeIngredient(ingredient, ingredientFile);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	public static void removeIngredient(Ingredient ingredient) {
		if (ingredient.getId() > 0) {
			File ingredientFile = new File(dataRoot, "ingredients/" + String.valueOf(ingredient.getId()));
			if (ingredientFile.exists()) {
				ingredientFile.delete();
			}
		}
	}
	
	public static Ingredient readIngredient(File file) throws IOException {
		DataInputStream stream = new DataInputStream(new FileInputStream(file));
		Ingredient ingredient = new Ingredient();
		ingredient.setId(stream.readInt());
		ingredient.setName(stream.readUTF());
		ingredient.setUnit(Unit.values()[stream.read()]);
		ingredient.setAlcoolRate(stream.readDouble());
		ingredient.setOrigin(Origin.values()[stream.read()]);
		ingredient.setUnitPrice(stream.readDouble());
		stream.close();
		return ingredient;
	}
	
	public static void writeIngredient(Ingredient ingredient, File file) throws IOException {
		DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
		stream.writeInt(ingredient.getId());
		stream.writeUTF(ingredient.getName());
		stream.write(ingredient.getUnit().ordinal());
		stream.writeDouble(ingredient.getAlcoolRate());
		stream.write(ingredient.getOrigin().ordinal());
		stream.writeDouble(ingredient.getUnitPrice());
		stream.close();
	}

	public static List<Cocktail> getAllCocktails() {
		File cocktailsDir = new File(dataRoot, "cocktails");
		List<Cocktail> result = new ArrayList<Cocktail>();
		try {
			if (cocktailsDir.exists()) {
				for (File file : cocktailsDir.listFiles()) {
					result.add(readCocktail(file));
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return result;
	}

	public static void saveOrUpdateCocktail(Cocktail cocktail) {
		if (cocktail.getId() == 0) {
			cocktail.setId(cocktailId++);
		}
		File ingredientFile = new File(dataRoot, "cocktails/" + String.valueOf(cocktail.getId()));
		ingredientFile.getParentFile().mkdirs();
		try {
			writeCocktail(cocktail, ingredientFile);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static Cocktail readCocktail(File file) throws IOException {
		Map<Integer, Ingredient> ingredientMap = new HashMap<Integer, Ingredient>();
		for (Ingredient ingredient : getAllIngredients()) {
			ingredientMap.put(ingredient.getId(), ingredient);
		}
		DataInputStream stream = new DataInputStream(new FileInputStream(file));
		Cocktail cocktail = new Cocktail();
		cocktail.setId(stream.readInt());
		cocktail.setName(stream.readUTF());
		int size = stream.readInt();
		for (int i = 0; i < size; ++i) {
			Ingredient ingredient = ingredientMap.get(stream.readInt());
			double quantity = stream.readDouble();
			if (ingredient != null) {
				cocktail.getIngredients().add(new QuantifiedIngredient(ingredient, quantity));
			}
		}
		stream.close();
		return cocktail;
	}

	public static void writeCocktail(Cocktail cocktail, File file) throws IOException {
		DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
		stream.writeInt(cocktail.getId());
		stream.writeUTF(cocktail.getName());
		stream.writeInt(cocktail.getIngredients().size());
		for (QuantifiedIngredient ingredient : cocktail.getIngredients()) {
			stream.writeInt(ingredient.getIngredient().getId());
			stream.writeDouble(ingredient.getQuantity());
		}
		stream.close();
	}

	public static void removeCocktail(Cocktail cocktail) {
		if (cocktail.getId() > 0) {
			File ingredientFile = new File(dataRoot, "cocktails/" + String.valueOf(cocktail.getId()));
			if (ingredientFile.exists()) {
				ingredientFile.delete();
			}
		}
	}

}
