package interface_adapter.FavView;

import use_case.FavView.FavViewInputBoundary;

public class FavViewController {

    final FavViewInputBoundary favViewInteractor;

    public FavViewController(FavViewInputBoundary favViewInteractor) {
        this.favViewInteractor = favViewInteractor;
    }

    public void execute() {

        favViewInteractor.execute();
    }
}
