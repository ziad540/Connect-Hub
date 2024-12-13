package BackEnd;

import java.util.ArrayList;

public class searchProcessor {  // hena asta5demna strategy design pattern 3shan law 7abena nezawed ay no3 search
    private SearchStrategy strategy;

    public searchProcessor(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public ArrayList<User> searchforusers(String name,String ID)
    {

        return strategy.searchforusers(name, ID);
    }


}
