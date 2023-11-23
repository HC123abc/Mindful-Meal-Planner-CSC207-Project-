package interface_adapter.FavouriteThis;

import use_case.favouriteThis.favouriteThisInputBoundary;
import use_case.favouriteThis.favouriteThisInputData;

public class favouriteThisController {
    final favouriteThisInputBoundary favouriteThisInteractor;

    public favouriteThisController(favouriteThisInputBoundary favouriteThisInteractor) {
        this.favouriteThisInteractor = favouriteThisInteractor;
    }

    //    CookThisOrReRollState.getTitle();
    public void execute(String title, String summary, String image,
                        String servings, String readyInMinutes,
                        String ingredients, String instruction,
                        String id, boolean isFavourite) {
        favouriteThisInputData fTInputData = new favouriteThisInputData(title,readyInMinutes,servings,summary,ingredients,instruction,image, id, isFavourite);

        favouriteThisInteractor.execute(fTInputData);
    }
}
