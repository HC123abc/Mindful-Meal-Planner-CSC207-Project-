package entity;

import org.json.JSONObject;

public interface SimpleRecipeFactoryInterface
{
    Recipe create(JSONObject recipeObject);
}
