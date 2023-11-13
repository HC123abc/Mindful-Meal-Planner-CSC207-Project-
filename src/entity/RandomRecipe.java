package entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class RandomRecipe {
//   have to check what the API call returns and how we will manage it
    private JSONArray randomRecipeList;
    private int currentRecipeIndex = 0;
    public void setRandomRecipeList(JSONArray randomRecipeList){
        this.randomRecipeList = randomRecipeList;
    }
    public JSONArray getRandomRecipeList(){
        return this.randomRecipeList;
    }

    public void setCurrentRecipeIndex(int i){
        currentRecipeIndex = i;
    }
    public int getCurrentRecipeIndex(){
        return currentRecipeIndex;
    }


}
