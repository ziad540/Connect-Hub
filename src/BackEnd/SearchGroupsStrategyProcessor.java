package BackEnd;

import java.util.ArrayList;

public class SearchGroupsStrategyProcessor {

    private SearchGroupStrategy searchGroupStrategy;

    public SearchGroupsStrategyProcessor(SearchGroupStrategy searchGroupStrategy) {
        this.searchGroupStrategy = searchGroupStrategy;
    }

    public ArrayList<String> groupsearch(String name, String ID)
    {
        return searchGroupStrategy.searchforgroups(name,ID);
    }
}
